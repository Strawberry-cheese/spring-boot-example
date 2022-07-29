package com.example.tribal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tribal.entity.Csdn;

import java.util.List;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/28 下午3:00
 */
public interface CsdnService extends IService<Csdn> {


    int insert1(List<Csdn> csdn);
}
