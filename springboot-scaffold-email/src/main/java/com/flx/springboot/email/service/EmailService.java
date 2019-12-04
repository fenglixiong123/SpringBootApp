package com.flx.springboot.email.service;

import com.flx.springboot.email.entity.SimpleMail;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 12:22
 * @Description:
 */
public interface EmailService {

    boolean sendSimpleEmail(SimpleMail simpleMail);

}
