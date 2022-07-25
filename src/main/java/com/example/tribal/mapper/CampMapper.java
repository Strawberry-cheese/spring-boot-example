package com.example.tribal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tribal.entity.Camp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 14:28
 **/
@Mapper
public interface CampMapper extends BaseMapper<Camp> {
}
