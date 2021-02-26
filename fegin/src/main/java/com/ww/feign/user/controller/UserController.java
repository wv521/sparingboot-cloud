package com.ww.feign.user.controller;

import com.ww.feign.user.pojo.User;
import com.ww.feign.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser")
    public User getUser(){
        User user = userService.getUserInfo();
        return user;
    }
}
