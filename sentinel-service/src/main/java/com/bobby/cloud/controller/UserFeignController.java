package com.bobby.cloud.controller;

import com.bobby.cloud.domain.User;
import com.bobby.cloud.domain.response.CommonResult;
import com.bobby.cloud.feign.UserRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: bait
 * @create: 2022-09-26 15:28
 * @description:
 **/
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserRouteService userRouteService;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id) {
        return userRouteService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username) {
        return userRouteService.getByUsername(username);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return userRouteService.create(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return userRouteService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return userRouteService.delete(id);
    }

}
