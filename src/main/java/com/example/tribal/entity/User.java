package com.example.tribal.entity;

import lombok.Data;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:25
 **/
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}