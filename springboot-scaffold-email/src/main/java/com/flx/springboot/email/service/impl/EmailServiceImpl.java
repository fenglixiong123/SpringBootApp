package com.flx.springboot.email.service.impl;

import com.flx.springboot.email.entity.ComplexMail;
import com.flx.springboot.email.entity.SimpleMail;
import com.flx.springboot.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 12:23
 * @Description:
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

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

    /**
     * 发送复杂邮件
     * @param complexMail
     * @return
     */
    @Override
    public boolean sendComplexEmail(ComplexMail complexMail){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setSubject(complexMail.getSubject());
            messageHelper.setFrom("fenglixiong123@163.com");
            messageHelper.setTo(complexMail.getTo());
            messageHelper.setText(complexMail.getContent(),true);
            try {
                mailSender.send(mimeMessage);
                return true;
            }catch (MailException e){
                log.error("发送失败：{}",e.getMessage());
                return false;
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
