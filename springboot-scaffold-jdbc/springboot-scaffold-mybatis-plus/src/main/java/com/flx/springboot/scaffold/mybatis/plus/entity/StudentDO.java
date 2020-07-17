package com.flx.springboot.scaffold.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDO;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;

/**
 * @author fanzhen
 * @date2018-12-18-16:59
 */
@TableName(value = "basic_student")
@Data
public class StudentDO extends BaseDO {

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
    @TableField(value = "student_vvv")
    private String studentVVV;

    /**
     * 状态标记
     * 有效(effective)，
     * 无效(invalid)
     * 删除(deleted)
     */
    private State state;

}
