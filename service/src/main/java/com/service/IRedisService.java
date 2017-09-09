package com.service;

import org.springframework.data.redis.connection.RedisConnection;

import java.io.UnsupportedEncodingException;

public interface IRedisService {

    RedisConnection getRedisConnection();

    void saveValue(String key, String value);

    String getValue(String key);
}
