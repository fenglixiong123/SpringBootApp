package com.flx.springboot.email.service;

import com.flx.springboot.email.entity.ComplexMail;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 12:22
 * @Description:
 */
public interface ComplexEmailService {

    boolean sendComplexEmail(ComplexMail complexMail);

}
