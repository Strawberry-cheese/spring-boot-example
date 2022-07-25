package com.example.tribal.controller;

import com.example.tribal.entity.Camp;
import com.example.tribal.entity.User;
import com.example.tribal.service.CampService;
import com.example.tribal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.debugger.DataSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:29
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CampService campService;

    @RequestMapping
    public List<User> list(){
        return userService.list();
    }


    @PostMapping("/add")
    public String add(){
        User user = new User();
        user.setAge(19);
        user.setName("老张");
        userService.add1(user);
        Camp camp = new Camp();
        camp.setUserId(user.getId());
        camp.setName("复仇者联盟");
        camp.setCodes("1");
        campService.add(camp);
        return user.getId().toString();
    }
}
