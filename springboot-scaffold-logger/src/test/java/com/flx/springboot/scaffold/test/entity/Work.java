package com.flx.springboot.scaffold.test.entity;

import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2018.12.04 10:38
 * @Description
 **/
@Data
public class Work {

    private int id;
    private String name;
    private String remark;

    public Work() {
    }

    public Work(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}
