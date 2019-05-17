package com.flx.springboot.scaffold.web.core.exception.element;

import com.flx.springboot.scaffold.web.core.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.web.core.exception.BaseException;

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
