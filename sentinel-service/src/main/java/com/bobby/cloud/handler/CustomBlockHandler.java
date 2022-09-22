package com.bobby.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bobby.cloud.domain.response.CommonResult;

/**
 * @author: bait
 * @create: 2022-09-20 15:58
 * @description:
 * 设置的自定义限流规则类中的方法应该为静态方法才能生效
 **/
public class CustomBlockHandler {

    public static CommonResult handleException(BlockException exception) {
        return new CommonResult("服务不可用", 5003);
    }
}
