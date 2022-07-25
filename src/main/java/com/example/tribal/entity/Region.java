package com.example.tribal.entity;

import lombok.Data;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-14 17:34
 **/
@Data
public class Region {

    private Integer regionId;

    private String regionName;

    private List<City> cityList;

}
