package com.bobby.hystrix.domain;

import lombok.Data;
/**
 * @author: Bobby
 * @create: 2021-05-20 17:37
 * @description:
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
