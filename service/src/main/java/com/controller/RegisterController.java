package com.controller;

import com.entity.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WTON on 2017/5/16.
 */
@Controller
public class RegisterController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password) {
        return null;
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(String username, String password) {
        User user = new User(username, password);
        return userService.save(user);

    }


}
