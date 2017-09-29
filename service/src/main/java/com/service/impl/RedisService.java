package com.service.impl;

import com.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Service
public class RedisService implements IRedisService {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Override
    public RedisConnection getRedisConnection() {
        return connectionFactory.getConnection();
    }

    @Override
    public void saveValue(String key, String value) {
        getRedisConnection().set(key.getBytes(), value.getBytes());
    }

    @Override
    public String getValue(String key) {
        byte[] bytes = getRedisConnection().get(key.getBytes());
        try {
            return new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveValueWithTime(String key, String value, Long time) {
    }

}
