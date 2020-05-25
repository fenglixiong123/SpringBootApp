package com.flx.springboot.scaffold.exception.element;


import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.exception.BaseException;

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
