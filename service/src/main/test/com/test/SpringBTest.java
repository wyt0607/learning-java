package com.test;

import com.Application;
import com.dao.IUserDao;
import com.entity.User;
import com.util.CommonUtil;
import com.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.UUID;

/**
 * Created by WTON on 2017/5/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBTest {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    @Rollback
    public void test(){
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
    public  void  testRedis(){
        // 保存字符串
        stringRedisTemplate.opsForValue().set("bbb", "123123");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

}
