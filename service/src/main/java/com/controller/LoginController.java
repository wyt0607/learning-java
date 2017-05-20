package com.controller;

import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by WTON on 2017/5/16.
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;
}
