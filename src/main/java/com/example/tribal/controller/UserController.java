package com.example.tribal.controller;

import com.example.tribal.annotation.PassToken;
import com.example.tribal.entity.Camp;
import com.example.tribal.entity.User;
import com.example.tribal.service.CampService;
import com.example.tribal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:29
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CampService campService;

    @RequestMapping
    public List<User> list() {
        return userService.list();
    }








    /**
     * 登陆后的操作，默认需要验证
     * @return
     */
    @PassToken
    @GetMapping("/message")
    public String getMessage() {
        return "验证通过";
    }

}
