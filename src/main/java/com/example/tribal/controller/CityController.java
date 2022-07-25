package com.example.tribal.controller;

import com.example.tribal.entity.City;
import com.example.tribal.entity.Region;
import com.example.tribal.service.CityService;
import dm.jdbc.stat.support.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-13 15:32
 **/
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/getCityList")
    public List<City> getCityList() {
        return cityService.getCityList();
    }


    @RequestMapping("getRegionList")
    public List<Region> getRegionList() {
        return cityService.getRegionList();
    }
    @RequestMapping("/add")
    @Cacheable(value = "city", key = "#cityId")
    public City add(@RequestParam(value = "cityId") String cityId) {
        City city = new City();
        city.setCityName("淄博");
        city.setRegionId(2);
        city.setCityId("ZB");
        cityService.insertCity(city);
        return city;
    }


    @RequestMapping("/del")
    public String del(@RequestParam("cityId") String cityId) {
        cityService.delCity(cityId);
        return "success";
    }


    @RequestMapping("/addJc")
    public String addJc() {
        City city = new City();
        city.setCityName("济南");
        city.setRegionId(2);
        city.setCityId("JN");
        cityService.insertCityJc(city);
        return "success";
    }


    @RequestMapping("/getCityListByPostGre")
    public List<City> getCityListByPostGre() {
        return cityService.getCityListByPostGre();
    }


    @RequestMapping("/addPostGre")
    public City addPostGre(){
        return cityService.addPostGre();
    }

}
