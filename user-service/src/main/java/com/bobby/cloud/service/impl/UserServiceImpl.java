package com.bobby.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobby.cloud.domain.User;
import com.bobby.cloud.mapper.UserMapper;
import com.bobby.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Bobby
 * @create: 2020-04-24 18:01
 * @description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void create(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUser(Long id) {
       /* List<User> findUserList = userList.stream().filter(userItem -> userItem.getId().equals(id)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        }
        return null;*/
        return userMapper.selectById(id);
    }

    @Override
    public void update(User user) {
       /* userList.stream().filter(userItem -> userItem.getId().equals(user.getId())).forEach(userItem -> {
            userItem.setUsername(user.getUsername());
            userItem.setPassword(user.getPassword());
        });*/
         userMapper.updateById(user);
    }

    @Override
    public void delete(Long id) {
        User user = userMapper.selectById(id);
        user.setIsDeleted("1");
        this.updateById(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> qr = new QueryWrapper();
        qr.like("username",username);
        return userMapper.selectOne(qr);
    }

    @Override
    public List<User> getUserByIds(List<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

}
