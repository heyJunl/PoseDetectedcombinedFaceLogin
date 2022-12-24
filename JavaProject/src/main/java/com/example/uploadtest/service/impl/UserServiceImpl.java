package com.example.uploadtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.uploadtest.entity.User;
import com.example.uploadtest.mapper.UserMapper;
import com.example.uploadtest.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Jun
 * @date 2022/12/7 21:51
 * @description UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
