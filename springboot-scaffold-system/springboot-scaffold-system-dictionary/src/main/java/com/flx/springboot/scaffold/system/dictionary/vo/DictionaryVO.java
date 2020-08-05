package com.flx.springboot.scaffold.system.dictionary.vo;

import com.flx.springboot.scaffold.mybatis.plus.base.BaseEntity;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;

/**
 * @author fenglixiong
 * @date2018-12-18-16:59
 */
@Data
public class DictionaryVO extends BaseEntity {

    private String keyCode;
    private String keyName;
    private String valueCode;
    private String i18nCode;
    private State state;

}
