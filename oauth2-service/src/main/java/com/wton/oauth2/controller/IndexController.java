package com.wton.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RestControllerAdvice
public class IndexController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public String index() {
        return "12345";
    }

    @PostMapping("/login")
    public String login(String user, String password) {
        return "12345";
    }

}
