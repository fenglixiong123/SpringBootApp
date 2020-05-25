package com.flx.springboot.scaffold.exception.element;

import com.flx.springboot.scaffold.common.enums.ErrorMsgEnum;
import com.flx.springboot.scaffold.exception.BaseException;

/**
 * @Author Fenglixiong
 * @Create 2018.11.10 14:06
 * @Description 自定义业务异常类
 **/
public class BizException extends BaseException {

    public BizException(String message) {
        super(message);
    }

    public BizException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }
}
