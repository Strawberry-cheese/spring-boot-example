package com.example.tribal.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.config.JobProcessor;
import com.example.tribal.config.MysqlPipeline;
import com.example.tribal.entity.JobInfo;
import com.example.tribal.mapper.JobInfoMapper;
import com.example.tribal.processor.SamplePageProcessor;
import com.example.tribal.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:44
 **/
@Service
@Slf4j
@DS("master")
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements JobInfoService {


    String url = "http://www.weather.com.cn/weather/101120101.shtml";


    @Autowired
    private MysqlPipeline mysqlPipeline;

    @Autowired
    private JobProcessor jobProcessor;

    @Autowired
    private SamplePageProcessor samplePageProcessor;

    public void getJobInfo() {
        log.info("开始爬取数据");

        //设置爬虫配置
        Spider.create(samplePageProcessor)
                //使用布隆过滤器过滤重复url,需要引入guava包
//                .addUrl("https://blog.csdn.net/qq_38225558/article/list/1")
                .addUrl("https://blog.csdn.net/qq_41185868/article/list/1")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(50) //设置线程数
                .addPipeline(mysqlPipeline) //设置持久化
                .run();

    }

    @Override
    public List<JobInfo> selectJobInfoByUrl(String url) {
        QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("url", url);
        List<JobInfo> jobInfos = baseMapper.selectList(wrapper);
        return jobInfos;

    }
}
