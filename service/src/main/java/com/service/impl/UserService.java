package com.service.impl;

import com.dao.IUserDao;
import com.entity.User;
import com.service.IUserService;
import com.util.CommonUtil;
import com.util.JWTUtil;
import org.json.JSONObject;
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

    private org.slf4j.Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Autowired
    private IUserDao userDao;

    @Override
    public String register(User user) {

        User result = userDao.findByUsername(user.getUsername());
        if (result == null) {
            userDao.save(user);
            return "success";
        }
        return "fail";
    }

    @Override
    public String login(User user) {
        User result = userDao.findByUsername(user.getUsername());
        if (CommonUtil.isEmpty(result)) {
            if (result.getPassword().equals(user.getPassword())) {
                Map map = CommonUtil.entityToMap(result);
                try {
                    String jwt = JWTUtil.createJWT(UUID.randomUUID().toString(), new JSONObject(map).toString(), 9999);
                    logger.info("用户" + user.getUsername() + "凭证:" + jwt);
                    return jwt;
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
