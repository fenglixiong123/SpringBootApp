package com.flx.springboot.scaffold.mybatis.plus.base;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2020/8/5 18:44
 * @Description:
 */
@Data
public class BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * createdUser
     */
    private String createUser;

    /**
     * createdTime
     */
    private Date createTime;

    /**
     * lastUpdatedUser
     */
    private String updateUser;

    /**
     * lastUpdatedTime
     */
    private Date updateTime;

}
