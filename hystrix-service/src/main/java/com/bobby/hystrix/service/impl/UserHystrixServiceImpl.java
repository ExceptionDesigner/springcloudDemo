package com.bobby.hystrix.service.impl;

import com.bobby.hystrix.domain.CommonResult;
import com.bobby.hystrix.domain.User;
import com.bobby.hystrix.service.UserHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author: Bobby
 * @create: 2021-05-20 17:30
 * @description:
 **/
@Service("userHystrixService")
public class UserHystrixServiceImpl implements UserHystrixService {

    private Logger log = LoggerFactory.getLogger(UserHystrixServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public CommonResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }


    public CommonResult getDefaultUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }


    /*
    fallbackMethod：指定服务降级处理方法；
    ignoreExceptions：忽略某些异常，不发生服务降级；
    commandKey：命令名称，用于区分不同的命令；
    groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
    threadPoolKey：线程池名称，用于划分线程池。*/
    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser",
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool")
    public CommonResult getUserCommand(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }


    //测试忽略异常
//    忽略掉 NullPointerException异常，那么这个地方就会
    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser2", ignoreExceptions = {NullPointerException.class})
    public CommonResult getUserException(Long id) {
        if (id == 1) {
            throw new NullPointerException();
        } else if (id == 2) {
            throw new IndexOutOfBoundsException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }


    public CommonResult getDefaultUser2(Long id) {
        User defaultUser = new User(-2L, "defaultUser2", "234567");
        return new CommonResult<>(defaultUser);
    }


//    @CacheResult：开启缓存，默认所有参数作为缓存的key，cacheKeyMethod可以通过返回String类型的方法指定key；
//    @CacheKey：指定缓存的key，可以指定参数或指定参数中的属性值为缓存key，cacheKeyMethod还可以通过返回String类型的方法指定；
    @Override
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public CommonResult getUserCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }


    /**
     * 为缓存生成key的方法
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }


    //    @CacheRemove：移除缓存，需要指定commandKey，以及获取
    @Override
    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public CommonResult removeCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, CommonResult.class, id);
    }




}
