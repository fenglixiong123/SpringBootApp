package com.flx.springboot.scaffold.system.dictionary.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseDO;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import lombok.Data;

/**
 * @author fanzhen
 * @date2018-12-18-16:59
 */
@TableName(value = "scaffold_dictionary")
@Data
public class DictionaryDO extends BaseDO {

    private String keyCode;
    private String keyName;
    private String valueCode;
    private String i18nCode;
    private State state;

}
