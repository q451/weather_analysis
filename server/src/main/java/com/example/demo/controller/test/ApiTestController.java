package com.example.demo.controller.test;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.codeEnum.Constants;
import com.example.demo.common.response.Result;
import com.example.demo.common.response.ResultUtil;
import com.example.demo.service.api.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yxp
 */
@RestController
@RequestMapping("api")
public class ApiTestController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ApiService apiService;

    @GetMapping("/weather")
    public String queryWeather() {
        String apiUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city=640522&key=" + "ee61a3941c42a82121568d7309b7c51e";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            String result = responseEntity.getBody();
            JSON.parseObject(result);
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }

    @GetMapping("/ip")
    public String queryIpLocation() {
        String apiUrl = "https://restapi.amap.com/v3/ip?ip=114.247.50.2&key=" + "ee61a3941c42a82121568d7309b7c51e";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }

    @GetMapping("/traffic")
    public String queryTraffic() {
        String apiUrl = "https://restapi.amap.com/v3/traffic/status/rectangle?rectangle=116.351147,39.966309;116.357134,39.968727&key=" + "ee61a3941c42a82121568d7309b7c51e";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (Constants.RESPONSE_SUCCESS == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }

    @GetMapping("/thisWeather")
    public Result tT() {
        return ResultUtil.success(apiService.getThisWeather(Constants.BEIJING));
    }

    @GetMapping("/hua")
    public Result XiHua() {
        return ResultUtil.success(apiService.getJoker());
    }

    @GetMapping("/new")
    public Result getNews() {
        return ResultUtil.success(apiService.getNews());
    }

    @GetMapping("/history")
    public Result getHistory() {
        return ResultUtil.success(apiService.getHistory());
    }
}
