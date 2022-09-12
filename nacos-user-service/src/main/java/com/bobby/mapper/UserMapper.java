package com.bobby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Bobby
 * @create: 2022-04-17 17:26
 * @description: 用户mapper
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserList();

}


