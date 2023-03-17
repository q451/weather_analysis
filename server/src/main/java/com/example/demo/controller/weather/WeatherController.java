package com.example.demo.controller.weather;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.base.JsonBase;
import com.example.demo.common.codeEnum.Constants;
import com.example.demo.common.codeEnum.ErrorCodeEnum;
import com.example.demo.common.response.Result;
import com.example.demo.common.response.ResultUtil;
import com.example.demo.model.weather.Weather;
import com.example.demo.service.weather.PageDto;
import com.example.demo.service.weather.PageInfo;
import com.example.demo.service.weather.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yxp
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    @Resource
    private WeatherService weatherService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 更新数据
     *
     * @return Result
     */
    @GetMapping("/updateWeather")
    public Result updateWeather() {
        boolean isTrue = weatherService.updateData();
        if(!isTrue){
            return ResultUtil.error(ErrorCodeEnum.TEST_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 所有天气列表
     *
     * @return Result
     */
    @GetMapping("/weatherList")
    public Result getWeatherList() {
        Map<String,Object> map = new HashMap<>(10);

        List<Weather> list = weatherService.lists();
        map.put("data",list);
        map.put("count",list.size());

        return ResultUtil.success(map);
    }

    /**
     * 分页
     *
     * @return Result
     */
    @PostMapping("/weatherListPage")
    public Result weatherListPage(@RequestBody PageDto dto) {
        IPage<Weather> page = weatherService.page(dto);

        PageInfo<Weather> pageInfo = new PageInfo<>(dto.getPageNo(), dto.getPageSize());
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(page.getRecords());

        return ResultUtil.success(pageInfo);
    }

    /**
     * 获取年度分析
     *
     * @return map
     */
    @GetMapping("/everyYearAnalysis")
    public Result everyYearAnalysis() {
        return ResultUtil.success(weatherService.everyYearAnalysis());
    }

    /**
     * 当前历史上的同一天数据
     *
     * @return map
     */
    @GetMapping("/getThisDayList")
    public Result getThisDayList() {
        return ResultUtil.success(weatherService.getThisDayList());
    }

    /**
     * 获取当天实时天气预报
     *
     * @return map
     */
    @GetMapping("/getThisDayLatest")
    public Result getThisDayLatest() {
        String apiUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city=110000&key=" + "ee61a3941c42a82121568d7309b7c51e";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            Map<String, Object> jsonMap = JsonBase.toObjectMap(responseEntity.getBody());
            System.out.println(jsonMap.get("lives"));
            return ResultUtil.success(jsonMap);
        } else {
            return ResultUtil.success(responseEntity.getStatusCodeValue());
        }
    }

    /**
     * 求每个约的平均值
     *
     * @return map
     */
    @GetMapping("/getMonthAgrTemperature")
    public Result getMonthAgrTemperature() {
        return ResultUtil.success(weatherService.getMonthAgrTemperature());
    }

    /**
     * 统计晴天，雨天，雾霾，雪天等统计
     *
     * @return map
     */
    @GetMapping("/getStatistics")
    public Result getStatistics() {
        return ResultUtil.success(weatherService.getStatistics());
    }

    /**
     * 获取很坐标
     *
     * @return map
     */
    @GetMapping("/getXData")
    public Result getHengData() {
        return ResultUtil.success(weatherService.getHengData());
    }
}
