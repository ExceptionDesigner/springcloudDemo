package com.bobby.hystrix.service;

import com.bobby.hystrix.domain.CommonResult;
import com.bobby.hystrix.domain.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.Future;

/**
 * @author: Bobby
 * @create: 2021-05-20 17:30
 * @description:
 **/
public interface UserHystrixService {
    CommonResult getUser( Long id);

    CommonResult getUserCommand( Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);

}
