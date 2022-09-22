package com.bobby.cloud.domain.response;

import lombok.Data;

/**
 * @author: Bobby
 * @create: 2020-04-24 16:47
 * @description: 定义返回对象
 **/
@Data
public class CommonResult<T> {

    private T data;
    private String message;
    private Integer code;


    public CommonResult() {
    }

    public CommonResult(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public CommonResult(String message, Integer code) {
        this(null, message, code);
    }

    public CommonResult(T data) {
        this(data, "操作成功", 200);
    }

}
