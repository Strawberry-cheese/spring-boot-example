package com.example.tribal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Table;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:25
 **/
@Data
@Table(name = "sys_user")
public class User {

    private Long id;

    private String name;

    private String userName;

    private String email;

    private String phone;

    private String address;

    private String jobNum;

    private String password;
}