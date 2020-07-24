package com.flx.springboot.scaffold.mybatis.plus.vo;

import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;

import java.util.Date;

/**
 * @author fenglixiong
 * @date2018-12-18-16:59
 */
@Data
public class StudentVO {

    /**
     * id
     */
    private Long id;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 名称
     */
    private String studentName;

    /**
     * 特殊字段
     */
    private String studentVVV;

    /**
     * 状态标记
     */
    private State state;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}
