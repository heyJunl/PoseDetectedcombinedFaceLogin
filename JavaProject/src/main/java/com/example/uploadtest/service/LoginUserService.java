package com.example.uploadtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.uploadtest.entity.LoginUser;

import java.util.List;

/**
 * @author Jun
 * @date 2022/12/10 11:03
 * @description LoginUserService
 */
public interface LoginUserService extends IService<LoginUser> {
    List<LoginUser> getAllUserFace();

    boolean faceAdd(LoginUser loginUser);
}
