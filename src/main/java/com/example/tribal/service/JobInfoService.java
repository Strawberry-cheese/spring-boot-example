package com.example.tribal.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.entity.JobInfo;
import com.example.tribal.mapper.JobInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:42
 **/
@DS("master")
public interface JobInfoService extends IService<JobInfo> {


    List<JobInfo> selectJobInfoByUrl(String url);


    void getJobInfo();




}
