package com.hedspi.nihongobe.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @GetMapping("/hello")
    public String hello(Authentication authentication){
        return "hello" + authentication.getCredentials();
    }

}
