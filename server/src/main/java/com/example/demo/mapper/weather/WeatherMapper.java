package com.example.demo.mapper.weather;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.weather.Weather;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author iu
 */
@Mapper
public interface WeatherMapper extends BaseMapper<Weather> {
}
