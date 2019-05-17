package com.flx.springboot.scaffold.web.core.exception;

import com.flx.springboot.scaffold.web.core.enums.ErrorMsgEnum;

/**
 * @Author Fenglixiong
 * @Create 2018.11.14 17:19
 * @Description
 **/
public class BaseException extends RuntimeException {

    private ErrorMsgEnum errorMsgEnum;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum.getMsg());
        this.errorMsgEnum = errorMsgEnum;
    }
}
