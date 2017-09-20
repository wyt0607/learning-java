package com.service.impl;

import com.dao.IUserDao;
import com.entity.User;
import com.service.IUserService;
import com.util.CommonUtil;
import com.util.JWTUtil;
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

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Autowired
    private IUserDao userDao;

    @Override
    public User register(String userName, String password) {
        User user = userDao.findByUsername(userName);
        if (CommonUtil.isEmpty(user)) {
            logger.info("用户注册成功");
            return userDao.save(user);
        } else {
            logger.info("用户注册失败");
            return null;
        }
    }

    @Override
    public User login(String userName, String password) {
        User user = userDao.findByUsername(userName);
        if (CommonUtil.isEmpty(user)) {
            if (user.getPassword().equals(password)) {
                Map map = CommonUtil.entityToMap(user);
                try {
                    String jwt = JWTUtil.createJWT(UUID.randomUUID().toString(), new JSONObject(map).toString(), 9999);
                    logger.info("用户" + user.getUsername() + "凭证:" + jwt);
                    return null;
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
