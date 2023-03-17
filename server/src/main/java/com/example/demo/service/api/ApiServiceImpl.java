package com.example.demo.service.api;

import com.alibaba.fastjson.JSON;
import com.example.demo.base.JsonBase;
import com.example.demo.common.codeEnum.Constants;
import com.example.demo.common.codeEnum.ErrorCodeEnum;
import com.example.demo.common.response.Result;
import com.example.demo.common.response.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author yxp
 */
@Service
public class ApiServiceImpl implements ApiService{

    @Resource
    private RestTemplate restTemplate;

    @Override
    public String getWeather(String locationCode, String key) {
        String apiUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city="+ locationCode+"&key=" + key;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }

    @Override
    public Result getThisWeather(String cityName) {
        String apiUrl = "http://apis.juhe.cn/simpleWeather/query?city="+ cityName +"&key=" + Constants.JU_HE_WEATHER_KEY;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            String result = responseEntity.getBody();
            JSON.parseArray(result);
            return ResultUtil.success(result);
        } else {
            return ResultUtil.error(ErrorCodeEnum.TEST_ERROR);
        }
    }

    @Override
    public Result getJoker() {
        Date date = new Date();
        Long time = date.getTime()/1000;

        String apiUrl = "http://v.juhe.cn/joke/content/list.php?key="+Constants.JU_HE_X_HUA_KEY +"&sort=desc&time="+time;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            String result = responseEntity.getBody();
            Map<String, Object> jsonMap = JsonBase.toObjectMap(result);
            return ResultUtil.success(jsonMap);
        } else {
            return ResultUtil.error(ErrorCodeEnum.TEST_ERROR);
        }
    }

    @Override
    public Result getNews() {
        String apiUrl = "http://v.juhe.cn/toutiao/index?key="+Constants.JU_HE_NEWS_KEY;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            String result = responseEntity.getBody();
            Map<String, Object> jsonMap = JsonBase.toObjectMap(result);
            return ResultUtil.success(jsonMap);
        } else {
            return ResultUtil.error(ErrorCodeEnum.TEST_ERROR);
        }
    }

    @Override
    public Result getHistory() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        String time = simpleDateFormat.format(date);
        String apiUrl = "http://v.juhe.cn/todayOnhistory/queryEvent.php?date=" + time +"key="+Constants.JU_HE_HISTORY_KEY;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            String result = responseEntity.getBody();
            Map<String, Object> jsonMap = JsonBase.toObjectMap(result);
            return ResultUtil.success(jsonMap);
        } else {
            return ResultUtil.error(ErrorCodeEnum.TEST_ERROR);
        }
    }
}
