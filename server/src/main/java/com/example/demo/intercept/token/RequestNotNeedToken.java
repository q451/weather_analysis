package com.example.demo.intercept.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 接口忽略token验证
 *
 * @author 杨小平
 * @date 2022年10月31日15:35:51
 */
@Configuration
public class RequestNotNeedToken implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtFilters())
                //拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns("/weather/getThisDayLatest")
                .excludePathPatterns("/weather/updateWeather")
                .excludePathPatterns("/weather/weatherList")
                .excludePathPatterns("/weather/weatherListPage")
                .excludePathPatterns("/weather/everyYearAnalysis")
                .excludePathPatterns("/weather/getThisDayList")
                .excludePathPatterns("/weather/getThisDayLatest")
                .excludePathPatterns("/weather/getMonthAgrTemperature")
                .excludePathPatterns("/weather/getStatistics");
    }
}
