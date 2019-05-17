package com.flx.springboot.scaffold.test.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author Fenglixiong
 * @Create 2018.12.04 10:37
 * @Description
 **/
@Data
public class Student {

    private int id;
    private String name;
    private int age;
    private Work work;
    private List<String> hobby;
    private Set<String> friends;
    private List<Work> works;

}
