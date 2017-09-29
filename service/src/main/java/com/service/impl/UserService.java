package com.service.impl;

import com.dao.IUserDao;
import com.entity.User;
import com.service.IUserService;
import com.util.CommonUtil;
import com.util.JWTUtil;
import org.apache.commons.collections4.SetValuedMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Created by WTON on 2017/5/16.
 */
@Service
public class UserService implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;
    @Autowired
    private RedisService redisService;


    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User register(String userName, String password) {
        User user = userDao.findByUserName(userName);
        if (!CommonUtil.isEmpty(user)) {
            user = userDao.save(new User(userName, password));
            logger.info("用户注册成功");
            return user;
        } else {
            logger.info("用户注册失败");
            return null;
        }
    }

    @Override
    public User login(String userName, String password) {
        User user = userDao.findByUserName(userName);
        if (CommonUtil.isEmpty(user)) {
            if (user.getPassword().equals(password)) {
                Map map = CommonUtil.entityToMap(user);
                map.remove("password");
                try {
                    String jwt = JWTUtil.createJWT(user.getId().toString(), new JSONObject(map).toString(), 999999);
                    logger.info("用户" + user.getUserName() + "凭证:" + jwt);
                    redisService.saveValue(user.getId().toString(), jwt);
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("密码不匹配");
            }
        }
        return null;
    }
}
