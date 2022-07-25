package com.example.tribal.entity;

import lombok.Data;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:25
 **/

@Data
public class City {

    private String cityId;

    private String cityName;

    private Integer regionId;

    private String regionName;

}
