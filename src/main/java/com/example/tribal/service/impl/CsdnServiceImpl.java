package com.example.tribal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.entity.Csdn;
import com.example.tribal.mapper.CsdnMapper;
import com.example.tribal.service.CsdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/28 下午3:00
 */

@Service
public class CsdnServiceImpl extends ServiceImpl<CsdnMapper, Csdn> implements CsdnService {


    @Autowired
    private CsdnMapper csdnMapper;


    @Override
    public int insert1(List<Csdn> csdnList) {

        return csdnMapper.insert1(csdnList);
    }
}
