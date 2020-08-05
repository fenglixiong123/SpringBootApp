package com.flx.springboot.scaffold.system.i18n.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;

import java.util.Date;

/**
 * @author fenglixiong
 * @date2018-12-18-16:59
 */
@Data
public class I18nVO extends BaseEntity {

    private String i18nCode;
    private String language;
    private String value;
    private State state;

}
