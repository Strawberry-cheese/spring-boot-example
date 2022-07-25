package com.example.tribal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tribal.entity.Camp;
import com.example.tribal.entity.User;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 14:29
 **/
public interface CampService extends IService<Camp> {

    /**
     * 数据新增
     * @param camp
     * @return
     */
    Integer add(Camp camp);
}
