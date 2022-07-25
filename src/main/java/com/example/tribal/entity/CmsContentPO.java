package com.example.tribal.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-25 15:28
 **/
@Data
public class CmsContentPO {

    private String contentId;

    private String title;

    private String content;

    private Date releaseDate;

}
