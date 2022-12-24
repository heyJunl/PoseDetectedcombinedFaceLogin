package com.example.uploadtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uploadtest.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jun
 * @date 2022/12/7 21:51
 * @description UserMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
