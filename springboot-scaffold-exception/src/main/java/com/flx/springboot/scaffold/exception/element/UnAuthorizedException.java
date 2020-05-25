package com.flx.springboot.scaffold.exception.element;

import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.14 17:23
 * @Description
 **/
public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
