package com.example.uploadtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.uploadtest.entity.LoginUser;
import com.example.uploadtest.mapper.LoginUserMapper;
import com.example.uploadtest.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jun
 * @date 2022/12/10 11:04
 * @description LoginUserServiceImpl
 */
@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public List<LoginUser> getAllUserFace() {
        return loginUserMapper.getAllUser();
    }

    @Override
    public boolean faceAdd(LoginUser loginUser) {
        return loginUserMapper.add(loginUser);
    }
}
