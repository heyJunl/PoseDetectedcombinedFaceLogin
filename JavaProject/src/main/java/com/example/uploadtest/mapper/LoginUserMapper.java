package com.example.uploadtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uploadtest.entity.LoginUser;
import com.example.uploadtest.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Jun
 * @date 2022/12/10 11:05
 * @description LoginUserMapper
 */
@Mapper
public interface LoginUserMapper extends BaseMapper<LoginUser> {

    @Insert("INSERT INTO login_user(username, extract) values(#{username}, #{extract})")
    Boolean add(LoginUser loginUser);

    @Select("SELECT * FROM login_user")
    List<LoginUser> getAllUser();
}
