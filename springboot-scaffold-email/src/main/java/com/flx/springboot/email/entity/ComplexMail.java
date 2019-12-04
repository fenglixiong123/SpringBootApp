package com.flx.springboot.email.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Fenglixiong
 * @Date: 2019/12/4 15:09
 * @Description:
 */
@Data
@NoArgsConstructor
public class ComplexMail {

    private String subject;
    private String to;
    private String from;
    private String content;


}
