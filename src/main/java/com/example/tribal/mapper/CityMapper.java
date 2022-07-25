package com.example.tribal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tribal.entity.Camp;
import com.example.tribal.entity.City;
import com.example.tribal.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot-example
 * @description
 * @author: 刘辉
 * @create: 2022-07-13 15:27
 **/
@Mapper
public interface CityMapper extends BaseMapper<City> {

    List<City> getCityList();

    Integer insertCity(City city);

    Integer insertGre(City city);

    Integer insertCityJc(City city);

    Integer delCity(String cityId);

    List<Region> getRegionList();

    List<City> getCityListByRegionId(Integer regionId);

    List<City> getCityDMList();

    List<City> getCityListByPostGre();
}
