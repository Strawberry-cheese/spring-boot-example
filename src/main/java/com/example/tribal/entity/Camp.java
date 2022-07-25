package com.example.tribal.entity;

import lombok.Data;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 14:28
 **/
@Data
public class Camp {
    private Long id;
    private Long userId;
    private String name;
    private String codes;
    private String describes;
}
