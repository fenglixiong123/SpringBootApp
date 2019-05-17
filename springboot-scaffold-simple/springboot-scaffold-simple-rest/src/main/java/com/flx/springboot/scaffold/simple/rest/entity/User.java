package com.flx.springboot.scaffold.simple.rest.entity;

import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2018.11.27 18:11
 * @Description
 **/
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String hobby;

    public User() {
    }

    public User(Long id, String name, Integer age, String hobby) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }
}
