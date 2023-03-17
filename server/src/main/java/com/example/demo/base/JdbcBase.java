package com.example.demo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author iu
 */
public class JdbcBase {
    @Autowired
    private static JdbcTemplate jdbcTemplate;

}
