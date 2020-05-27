package com.flx.springboot.scaffold.application.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Fenglixiong
 * @Date: 2020/5/27 17:05
 * @Description:
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String work;
    private Date birth;

}
