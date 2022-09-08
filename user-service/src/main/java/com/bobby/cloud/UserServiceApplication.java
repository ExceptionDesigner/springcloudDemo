package com.bobby.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//是否注册到eureka注册中心
//@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.bobby.cloud.mapper"})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
