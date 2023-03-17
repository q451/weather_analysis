package com.example.demo.model.weather;

import lombok.Data;

/**
 * 用户
 * @author iu
 */
@Data
public class Weather {

    private Long id;

    private String date;

    private String week;

    private String max;

    private String min;

    private String weather;

    private String wind;
}
