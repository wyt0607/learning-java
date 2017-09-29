package com.controller;

import com.entity.User;
import com.service.IRedisService;
import com.service.IUserService;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userService")
public class UserServiceController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRedisService redisService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password) {
        User user = userService.register(username.trim(), password.trim());
        if (user != null) {
            return "成功";
        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        User user = userService.login(username.trim(), password.trim());
        String jwt = redisService.getValue(user.getId().toString());
        return jwt;
    }

    @RequestMapping(value = "/test")
    public String test(String data) {
        System.out.println(data);
        return null;

    }
}

