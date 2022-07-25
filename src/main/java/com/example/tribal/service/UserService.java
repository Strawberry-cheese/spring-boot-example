package com.example.tribal.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tribal.entity.User;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:27
 **/

public interface UserService extends IService<User> {


    Integer add1(User user);
}
