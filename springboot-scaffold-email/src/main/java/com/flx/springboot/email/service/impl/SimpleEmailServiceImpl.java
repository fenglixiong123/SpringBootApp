package com.flx.springboot.email.service.impl;

import com.flx.springboot.email.entity.SimpleMail;
import com.flx.springboot.email.service.SimpleEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 12:23
 * @Description:
 */
@Slf4j
public class SimpleEmailServiceImpl implements SimpleEmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     * @param simpleMail
     * @return
     */
    @Override
    public boolean sendSimpleEmail(SimpleMail simpleMail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fenglixiong123@163.com");
        message.setSubject(simpleMail.getSubject());
        message.setTo(simpleMail.getTo());
        message.setText(simpleMail.getContent());
        try {
            mailSender.send(message);
        }catch (MailException e){
            log.error("发送失败：{}",e.getMessage());
            return false;
        }
        return true;
    }

}
