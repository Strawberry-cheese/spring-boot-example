package com.example.tribal.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.entity.User;
import com.example.tribal.mapper.UserMapper;
import com.example.tribal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:28
 **/
@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @DS("master")
    public Integer add1(User user) {
        return userMapper.insert(user);
    }

    @Override
    @DS("master")
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }
}
