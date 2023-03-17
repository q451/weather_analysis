package com.example.demo.service.weather;

import lombok.Data;

/**
 * @author yxp
 */
@Data
public class PageDto {

    private String date;

    private String weather;

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    public void verify() {
        if (null == pageNo || pageNo < 1) {
            pageNo = 1;
        }
        if (null == pageSize || pageSize <= 0) {
            pageSize =10;
        }
    }
}
