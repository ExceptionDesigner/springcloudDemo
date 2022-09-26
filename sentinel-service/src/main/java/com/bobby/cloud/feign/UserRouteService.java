package com.bobby.cloud.feign;

import com.bobby.cloud.domain.User;
import com.bobby.cloud.domain.response.CommonResult;
import com.bobby.cloud.feign.fallback.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: bait
 * @create: 2022-09-22 10:45
 * @description:
 **/
@FeignClient(value = "nacos-user-service",fallback= UserFallbackService.class)
public interface UserRouteService {

    @PostMapping("/user/create")
    public abstract CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    public abstract CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    public abstract CommonResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    public abstract CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    public abstract CommonResult delete(@PathVariable Long id);
}
