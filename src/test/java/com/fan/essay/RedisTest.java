package com.fan.essay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        // 往redis中存储数据
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("aa", "iaa",15, TimeUnit.SECONDS);
        System.out.println(operations.get("name"));

    }
}
