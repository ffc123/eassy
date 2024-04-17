package com.fan.essay.interceptors;

import com.fan.essay.utils.ThreadLocalUtil;
import com.fan.essay.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  令牌验证
        request.getHeader("Authorization");

        try {

            // 解析令牌
            Map<String, Object> token = JwtUtil.parseToken(request.getHeader("Authorization"));

            // 将数据储存到ThreadLocal中
            ThreadLocalUtil.set(token);

            // 获取ThreadLocal中的数据
            Map<String,Object> map = ThreadLocalUtil.get();
            String username = (String) map.get("username");
            // 从redis中获取token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(username + ":token");
            // 判断redis中的token是否存在
            if (redisToken == null) {
                throw new RuntimeException();
            }

            // 放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
