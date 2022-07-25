package com.example.tribal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tribal.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:21
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
