package com.example.demo.service.weather;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.weather.Weather;

import java.util.List;
import java.util.Map;

/**
 * @author yxp
 */
public interface WeatherService extends IService<Weather> {

    /**
     * 获取天气
     *
     * @return BlogUserAccount
     */
    List<Weather> lists();

    /**
     * 分页获取
     *
     * @param dto 分页对象
     * @return BlogUserAccount
     */
    IPage<Weather> page(PageDto dto);

    /**
     * 获取年度分析
     *
     * @return map
     */
    Map<String, List<Float>> everyYearAnalysis();

    /**
     * 当前历史上的同一天数据
     *
     * @return map
     */
    List<Weather> getThisDayList();

    /**
     * 求每个约的平均值
     *
     * @return list
     */
    Map<String,List<Float>> getMonthAgrTemperature();

    /**
     * 统计晴天，雨天，雾霾，雪天等统计
     *
     * @return map
     */
    Map<String,Integer> getStatistics();

    /**
     * 统计晴天，雨天，雾霾，雪天等统计
     *
     * @return map
     */
    List<String> getHengData();

    /**
     * 更新天气
     *
     * @return boolean
     */
    boolean updateData();
}
