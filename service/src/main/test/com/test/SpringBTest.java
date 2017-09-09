package com.test;

import com.Application;
import com.dao.IUserDao;
import com.entity.User;
import com.util.CommonUtil;
import com.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by WTON on 2017/5/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Test
    @Rollback
    public void test() {
        User user = userDao.findByUsername("wyt");
        Map map = CommonUtil.entityToMap(user);
        JSONObject jsonObject = new JSONObject(map);
        try {
            String jwt = JWTUtil.createJWT("123", jsonObject.toString(), 30000L);
            System.out.println(jwt);
            Claims claims = JWTUtil.parseJWT(jwt);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedis() {
        RedisConnection connection = redisConnectionFactory.getConnection();

        connection.set("xixi".getBytes(), "前二天".getBytes());

        byte[] bytes = connection.get("xixi".getBytes());
        byte[] bytes1 = connection.get("xiexie".getBytes());
        String s1 = new String(bytes1);
        String s = new String(bytes);
        System.out.println(s);
    }

}
