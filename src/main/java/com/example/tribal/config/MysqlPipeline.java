package com.example.tribal.config;

import com.example.tribal.entity.JobInfo;
import com.example.tribal.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:53
 **/
@Component
@Slf4j
public class MysqlPipeline  implements Pipeline
{
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的数据
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            jobInfoService.save(jobInfo);
         //   jobInfoService.save(jobInfo);
        }
    }
}

