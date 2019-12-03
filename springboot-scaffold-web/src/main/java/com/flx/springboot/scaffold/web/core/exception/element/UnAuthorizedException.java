package com.flx.springboot.scaffold.web.core.exception.element;

import com.flx.springboot.scaffold.web.core.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.web.core.exception.BaseException;

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
