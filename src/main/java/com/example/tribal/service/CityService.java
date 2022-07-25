package com.example.tribal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tribal.entity.City;
import com.example.tribal.entity.Region;
import com.example.tribal.entity.User;
import dm.jdbc.stat.support.json.JSONArray;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-12 11:27
 **/
public interface CityService extends IService<City> {
    List<City> getCityList();

    List<City> getCityListByPostGre();

    List<Region> getRegionList();

    Integer insertCity(City city);

    Integer insertCityJc(City city);

    Integer delCity(String cityId);


    List<City> getCityDMList();

    City addPostGre();
}
