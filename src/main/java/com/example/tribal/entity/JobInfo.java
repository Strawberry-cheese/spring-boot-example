package com.example.tribal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 13:41
 **/
@Data
@TableName("job_info")
@Slf4j
public class JobInfo {

    @TableId
    private Long id;
    /**
     * 公司名
     */
    private String companyName;
    /**
     * 公司地址
     */
    private String companyAddr;
    /**
     * 工作名称
     */
    private String jobName;
    /**
     * 工作地址
     */
    private String jobAddr;
    /**
     * 工作详情
     */
    private String jobDetail;
    /**
     * 薪资
     */
    private String salary;
    /**
     * 爬取的url
     */
    private String url;
    /**
     * 职位发布时间
     */
    private String time;

}
