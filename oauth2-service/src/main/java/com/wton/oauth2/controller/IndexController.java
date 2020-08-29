package com.wton.oauth2.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@RestControllerAdvice
public class IndexController {

    @ApiIgnore
    @GetMapping
    public ModelAndView toDoc() {
        return new ModelAndView("doc.html");
    }

    @PostMapping("/login")
    public String login(String user, String password) {
        return "12345";
    }

}
