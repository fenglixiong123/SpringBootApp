package com.flx.springboot.scaffold.web.core.exception.element;

import com.flx.springboot.scaffold.web.core.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.web.core.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.14 17:17
 * @Description
 **/
public class AccessForbiddenException extends BaseException {

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }

}
