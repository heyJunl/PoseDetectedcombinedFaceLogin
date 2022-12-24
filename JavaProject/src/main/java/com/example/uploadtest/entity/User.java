package com.example.uploadtest.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jun
 * @date 2022/12/7 21:43
 * @description User
 */
@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "序号", ordinal = 1)
    private Integer id;

    @JSONField(name = "姓名", ordinal = 2)
    private String userName;

    @JSONField(name = "成绩", ordinal = 3)
    private String userNum;
}
