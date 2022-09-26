package com.bobby.cloud.feign.fallback;

import com.bobby.cloud.domain.User;
import com.bobby.cloud.domain.response.CommonResult;
import com.bobby.cloud.feign.UserRouteService;
import org.springframework.stereotype.Component;

/**
 * @author: bait
 * @create: 2022-09-22 11:00
 * @description:
 **/
@Component
public class UserFallbackService implements UserRouteService {

    @Override
    public CommonResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,"服务降级返回",200);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,"服务降级返回",200);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser,"服务降级返回",200);
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用失败，服务被降级",500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用失败，服务被降级",500);
    }
}
