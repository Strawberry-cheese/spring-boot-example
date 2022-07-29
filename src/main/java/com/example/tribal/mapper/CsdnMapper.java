package com.example.tribal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tribal.entity.City;
import com.example.tribal.entity.Csdn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lhui    919238538@qq.com:
 * @description
 * @date 2022/7/28 下午3:01
 */
@Mapper
public interface CsdnMapper extends BaseMapper<Csdn> {

    int insert1(@Param("csdnList") List<Csdn> csdnList);
}
