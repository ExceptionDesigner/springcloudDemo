package com.bobby.cloud.domain;

import lombok.Data;

/**
 * @author: Bobby
 * @create: 2020-04-24 16:58
 * @description: 用户实体
 **/
@Data
public class User {

    private Long id;
    private String username;
    private String password;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
