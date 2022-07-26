package com.example.tribal.controller;

import com.example.tribal.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:56
 **/
@RestController
public class JobInfoController {
    @Autowired
    private JobInfoService jobInfoService;

    @GetMapping("/getJobInfo")
    public String getJobInfo() {
        jobInfoService.getJobInfo();
        return "success";
    }

}
