package com.example.uploadtest.entity;

/**
 * @author Jun
 * @date 2022/12/1 10:11
 * @description TOrganization
 */
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 组织管理表
 */
@Data
@TableName("t_organization")
public class TOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 党组织名称
     */
    private String oName;

    /**
     * 党组织代码
     */
    private String oCode;

    /**
     * 党组织类别
     */
    private String oCategory;

    /**
     * 已录入人数
     */
    private String oEntered;

    /**
     * 联系人姓名
     */
    private String oUsername;

    /**
     * 联系人联系方式
     */
    private String oPhone;

    /**
     * 状态码
     */
    private String isDeleted;

}
