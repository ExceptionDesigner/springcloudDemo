package com.bobby.hystrix.service;

import com.bobby.hystrix.domain.CommonResult;

/**
 * @author: Bobby
 * @create: 2021-05-20 17:30
 * @description:
 **/
public interface UserHystrixService {
    CommonResult getUser(Long id);
}
