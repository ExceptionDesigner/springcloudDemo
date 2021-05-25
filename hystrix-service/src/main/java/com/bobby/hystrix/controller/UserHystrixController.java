package com.bobby.hystrix.controller;

import com.bobby.hystrix.domain.CommonResult;
import com.bobby.hystrix.domain.User;
import com.bobby.hystrix.service.UserHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    //服务降级
    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        return userHystrixService.getUser(id);
    }



    @GetMapping("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable Long id) {
        return userHystrixService.getUserCommand(id);
    }


    //忽略异常
    @GetMapping("/testException/{id}")
    public CommonResult testException(@PathVariable Long id) {
        return userHystrixService.getUserException(id);
    }


    //缓存测试，看日志调用了几次 getUserCache 方法
    // 这个地方的缓存是针对一次请求，但是下次请求这个接口是不会缓存的
    @GetMapping("/testCache/{id}")
    public CommonResult testCache(@PathVariable Long id) {
        userHystrixService.getUserCache(id);
        userHystrixService.getUserCache(id);
        userHystrixService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }


    //移除缓存数据
    @GetMapping("/testRemoveCache/{id}")
    public CommonResult testRemoveCache(@PathVariable Long id) {
        userHystrixService.getUserCache(id);
        userHystrixService.removeCache(id);
        userHystrixService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }




}
