package com.bobby.hystrix.controller;

import com.bobby.hystrix.domain.CommonResult;
import com.bobby.hystrix.service.UserHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Bobby
 * @create: 2021-05-20 17:22
 * @description: 演示Hystrix试服务降级
 **/
@RestController
@RequestMapping("/user")
public class UserHystrixController {

    @Autowired
    private UserHystrixService userHystrixService;

    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        return userHystrixService.getUser(id);
    }
}
