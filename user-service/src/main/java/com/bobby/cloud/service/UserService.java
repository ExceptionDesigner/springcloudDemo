package com.bobby.cloud.service;

import com.bobby.cloud.domain.User;

import java.util.List;

/**
 * @author: Bobby
 * @create: 2020-04-24 17:58
 * @description:
 **/
public interface UserService {
    void create(User user);

    User getUser(Long id);

    List<User> getUserByIds(List<Long> ids);

    User getByUsername(String username);

    void update(User user);

    void delete(Long id);
}
