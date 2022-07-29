package com.example.tribal.config;

import com.example.tribal.entity.Csdn;
import com.example.tribal.entity.JobInfo;
import com.example.tribal.service.CsdnService;
import com.example.tribal.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;


/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:53
 **/
@Component
@Slf4j
public class MysqlPipeline implements Pipeline {
    @Autowired
    private CsdnService csdnService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的数据
        List<Csdn> csdnList = resultItems.get("csdnInfo");

        if (csdnList!=null) {
          csdnService.insert1(csdnList);
        }
//        if (jobInfo != null) {
//            jobInfoService.save(jobInfo);
//         //   jobInfoService.save(jobInfo);
//        }
    }
}

