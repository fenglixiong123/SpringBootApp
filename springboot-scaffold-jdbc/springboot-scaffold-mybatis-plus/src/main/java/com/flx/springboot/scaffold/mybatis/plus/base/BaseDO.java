package com.flx.springboot.scaffold.mybatis.plus.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * Base实体类
 *
 * @author fenglixiong
 * @date 2018-08-09 19:59
 */
@Data
public class BaseDO extends Model<BaseDO> {
    /**
     * PK
     */
    @TableId(type = IdType.AUTO)
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
