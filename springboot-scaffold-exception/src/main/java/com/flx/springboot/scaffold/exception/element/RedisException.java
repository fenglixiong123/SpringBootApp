package com.flx.springboot.scaffold.exception.element;

import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.exception.BaseException;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/26 18:51
 * @Description:
 */
public class RedisException extends BaseException {

    public RedisException(String message) {
        super(message);
    }

    public RedisException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
