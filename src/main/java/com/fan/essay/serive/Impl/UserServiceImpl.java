package com.fan.essay.serive.Impl;


import com.fan.essay.mapper.UserMapper;
import com.fan.essay.utils.Md5Util;
import com.fan.essay.utils.ThreadLocalUtil;
import com.fan.essay.pojo.User;
import com.fan.essay.serive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String username, String password) {
        // 加密
        String md5String = Md5Util.getMD5String(password);
        // 注册
        userMapper.add(username, md5String);

    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String md5String = Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(md5String, id);
    }

    @Override
    public User selectByName(String username) {
        User u = userMapper.selectByName(username);
        return u;
    }
}
