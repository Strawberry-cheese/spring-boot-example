package com.example.tribal.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tribal.entity.City;
import com.example.tribal.entity.Region;
import com.example.tribal.mapper.CityMapper;
import com.example.tribal.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-13 15:28
 **/
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @DS("dm")
    public List<City> getCityList() {
        return cityMapper.getCityList();
    }

    @Override
    @DS("postgre")
    public List<City> getCityListByPostGre() {
        return cityMapper.getCityListByPostGre();
    }

    @Override
    public List<Region> getRegionList() {
        List<Region> list = cityMapper.getRegionList();


        for (int i = 0; i < list.size(); i++) {
            Region region = list.get(i);
            List<City> cityList = cityMapper.getCityListByRegionId(region.getRegionId());
            region.setCityList(cityList);

        }
        return list;
    }


    @Override
    @DS("dm")
    public List<City> getCityDMList() {

        return cityMapper.getCityDMList();
    }

    @Override
    @DS("dm")
    public Integer insertCity(City city) {
        return cityMapper.insertCity(city);
    }

    @Override
    @DS("kingbase")
    public Integer insertCityJc(City city) {
        return cityMapper.insertCityJc(city);
    }

    @Override
    @DS("dm")
    public Integer delCity(String cityId) {
        return cityMapper.delCity(cityId);
    }

    @Override
    @DS("postgre")
    public City addPostGre() {
        City city = new City();
        city.setCityId("SH");
        city.setCityName("上海");
        city.setRegionId(2);
        cityMapper.insertGre(city);
        return city;

    }
}
