package com.fan.essay.controller;

import com.fan.essay.pojo.Result;
import com.fan.essay.pojo.User;
import com.fan.essay.serive.UserService;
import com.fan.essay.utils.JwtUtil;
import com.fan.essay.utils.Md5Util;
import com.fan.essay.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    // 5~16位非空字符
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {

        User u = userService.selectByName(username);
        if (u != null) {
            return Result.error("用户名已存在");
        } else {
            userService.register(username, password);
            return Result.success();
        }
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.selectByName(username);
        if (u == null) {
            return Result.error("用户名不存在");
        } else if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            // 生成JWT token令牌
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("id", u.getId());
            String token = JwtUtil.genToken(map);

            // 把token存储到redis中
            stringRedisTemplate.opsForValue().
                    set(username+":token", token,1, TimeUnit.HOURS);

            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }


    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        /*Map<String, Object> claims = JwtUtil.parseToken(token);
        String username = (String) claims.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.selectByName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token){
        // 1. 获取参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // 2. 校验参数
        if (!StringUtils.hasLength(oldPwd)
                || !StringUtils.hasLength(newPwd)
                || !StringUtils.hasLength(rePwd)) {
            return Result.error("密码不能为空");
        }

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        userService.selectByName(username);

        // 判断旧密码是否正确
        if (!Md5Util.getMD5String(oldPwd).equals(userService.selectByName(username).getPassword())) {
            return Result.error("旧密码错误");
        }

        // 判断新密码是否一致
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次密码不一致");
        }

        // 3.调用service
        userService.updatePwd(newPwd);

        // 4.删除redis中的token
        stringRedisTemplate.delete(username + ":token");
        System.out.println(username);

        return Result.success();
    }

}


