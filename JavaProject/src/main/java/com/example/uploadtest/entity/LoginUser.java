package com.example.uploadtest.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jun
 * @date 2022/12/10 9:38
 * @description LoginUser
 */
@Data
@TableName("login_user")
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    private String username;

    private byte[] extract;


}
