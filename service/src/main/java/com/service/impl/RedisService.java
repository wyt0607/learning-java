package com.service.impl;

import com.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

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
        return Arrays.toString(getRedisConnection().get(key.getBytes()));
    }
}
