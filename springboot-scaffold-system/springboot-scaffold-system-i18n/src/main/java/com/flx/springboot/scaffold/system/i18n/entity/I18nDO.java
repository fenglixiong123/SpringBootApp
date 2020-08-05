package com.flx.springboot.scaffold.system.i18n.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDO;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fanzhen
 * @date2018-12-18-16:59
 */
@TableName(value = "scaffold_i18n")
@Data
public class I18nDO extends BaseDO {

    private String i18nCode;
    private String language;
    private String value;
    private State state;

}
