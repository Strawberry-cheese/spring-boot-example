package com.example.tribal.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.example.tribal.entity.JobInfo;
import com.example.tribal.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:47
 **/
@Component
@Slf4j
public class JobProcessor implements PageProcessor {


    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(Page page) {

        //解析列表页
        List<Selectable> nodes = page.getHtml().css("div#resultList div.el").nodes();

        if (CollUtil.isEmpty(nodes)) {
            //为空表示这是招聘详情页,解析页面,获取招聘详情信息,保存数据
            try {
                this.saveJobInfo(page);
            } catch (Exception e) {
                log.error("解析异常,异常原因:{}", e.getMessage(), e);
            }
        } else {
            //不为空表示这是列表页,解析出详情页url,放到任务队列中
            for (Selectable node : nodes) {
                //获取url地址
                String jobInfoUrl = node.css("p.t1 span a").links().toString();
                if (StrUtil.isNotBlank(jobInfoUrl)) {
                    //判断记录是否已存在
                    List<JobInfo> jobInfoList = jobInfoService.selectJobInfoByUrl(jobInfoUrl);
                    if (CollUtil.isEmpty(jobInfoList)) {
                        //把url放到任务队列中
                        page.addTargetRequest(jobInfoUrl);
                    } else {
                        log.info("记录已存在,记录url:{}", jobInfoUrl);
                    }
                }
            }

            //获取下一页的url
            List<String> all = page.getHtml().css("div.p_in li.bk a").links().all();
            String bkUrl = all.get(all.size() - 1);
            log.info("下一页Url:{}", bkUrl);
            if (StrUtil.containsAny(bkUrl, "11.html")) {
                System.out.println("已查到10页数据,无须无限爬取数据");
                return;
            }

            page.addTargetRequest(bkUrl);
        }

    }


    /**
     * 解析job详情页
     *
     * @param page
     */
    private void saveJobInfo(Page page) {
        //解析页面
        Html html = page.getHtml();

        String companyName = html.css("clearfix cnav", "text").get();
        List<String> text = html.css("div.bmsg.inbox p.fp", "text").all();
        String companyAddr = text.get(text.size() - 1);
        String jobName = html.css("div.cn h1", "text").get();
        String jobStr = html.css("p.msg.ltype", "text").get();
        String[] s = StrUtil.split(jobStr, "    ");
        String jobAddr = s[0];
        String time = "";
        for (String s1 : s) {
            if (StrUtil.containsAny(s1, "发布")) {
                time = StrUtil.removeAll(s1, "发布");
                break;
            }
        }
        String jonDetail = html.css("div.bmsg.job_msg.inbox", "allText").get();
        String url = page.getUrl().get();
        String salary = html.css("div.in div.cn strong", "text").get();

        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName(jobName);
        jobInfo.setJobAddr(jobAddr);
        jobInfo.setJobDetail(jonDetail);
        jobInfo.setSalary(salary);
        jobInfo.setUrl(url);
        jobInfo.setTime(time);
        jobInfo.setCompanyName(companyName);
        jobInfo.setCompanyAddr(companyAddr);

        //把结果保存到resultItems,为了持久化
        page.putField("jobInfo", jobInfo);

    }

    //配置爬虫信息
    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            .setCharset("UTF-8")
            .setTimeOut(10 * 1000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);


    @Override
    public Site getSite() {
        return site;
    }
}
