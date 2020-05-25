package com.flx.springboot.scaffold.exception.element;


import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.16 16:52
 * @Description
 **/
public class ParamException extends BaseException {

    public ParamException(String message) {
        super(message);
    }

    public ParamException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }

}
