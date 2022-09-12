package com.bobby.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobby.domain.User;

import java.util.List;

/**
 * @author: Bobby
 * @create: 2020-04-24 17:58
 * @description:
 **/
public interface UserService extends IService<User> {
    void create(User user);

    User getUser(Long id);

    List<User> getUserByIds(List<Long> ids);

    User getByUsername(String username);

    void update(User user);

    void delete(Long id);

    List<User> getUserList();
}
