package com.flx.springboot.email.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 15:03
 * @Description:
 */
@Data
@NoArgsConstructor
public class SimpleMail {

    private String subject;
    private String to;
    private String from;
    private String content;

    public SimpleMail(String subject, String to, String from, String content) {
        this.subject = subject;
        this.to = to;
        this.from = from;
        this.content = content;
    }
}
