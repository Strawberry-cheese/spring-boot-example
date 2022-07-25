package com.example.tribal.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.entity.Camp;
import com.example.tribal.entity.User;
import com.example.tribal.mapper.CampMapper;
import com.example.tribal.mapper.UserMapper;
import com.example.tribal.service.CampService;
import com.example.tribal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 14:29
 **/
@Service
public class CampServiceImpl extends ServiceImpl<CampMapper, Camp> implements CampService {

    @Autowired
    private CampMapper campMapper;

    @Override
    @DS("slave")
    public Integer add(Camp camp) {
        return campMapper.insert(camp);
    }
}
