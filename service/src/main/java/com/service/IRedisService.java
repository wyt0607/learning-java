package com.service;

import org.springframework.data.redis.connection.RedisConnection;

public interface IRedisService {

    RedisConnection getRedisConnection();

    void saveValue(String key, String value);

    String getValue(String key);
}
