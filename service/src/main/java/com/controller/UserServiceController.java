package com.controller;

import com.entity.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userService")
public class UserServiceController {
    @Autowired
    private IUserService userService;

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
        userService.login(username.trim(), password.trim());
        return null;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(String username, String password) {
        userService.register(username.trim(), password.trim());
        return null;

    }
}
