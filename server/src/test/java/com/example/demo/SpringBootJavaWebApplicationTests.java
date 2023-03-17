package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;


@SpringBootTest
class SpringBootJavaWebApplicationTests {
	//这是redis封装的类
	@Autowired
	private JavaMailSender javaMailSender ;
}
