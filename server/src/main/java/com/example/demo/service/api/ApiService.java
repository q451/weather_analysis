package com.example.demo.service.api;

import com.example.demo.common.response.Result;

/**
 * @author yxp
 */
public interface ApiService {
    /**
     * 根据位置编码获取天气情况
     *
     * @param locationCode 编码
     * @param key 高德key
     * @return JSON
     */
    String getWeather(String locationCode, String key);

    /**
     * 获取一地天气
     *
     * @param cityName 名
     * @return 返回
     */
    Result getThisWeather(String cityName);

    /**
     * 获取笑话
     *
     * @return 返回
     */
    Result getJoker();

    /**
     * 获取新闻
     *
     * @return 返回
     */
    Result getNews();

    /**
     * 历史的今天
     *
     * @return 返回
     */
    Result getHistory();
}
