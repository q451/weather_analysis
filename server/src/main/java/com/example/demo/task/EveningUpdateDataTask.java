package com.example.demo.task;

import com.example.demo.service.weather.WeatherService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;

/**
 * @Author: 杨小平
 * @Description: 扫描数据库配置的定时任务，根据cron执行；支持定时任务动态添加修改
 */

@Configuration
@EnableScheduling
public class EveningUpdateDataTask implements SchedulingConfigurer {

    @Resource
    private WeatherService weatherService;

    private String cron = "0 0 23 * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
            //这里是执行任务,可以是一个任务或者多个任务
            () -> {
                weatherService.updateData();
            },
            //设置触发器
            triggerContext -> {
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        );
    }

}
