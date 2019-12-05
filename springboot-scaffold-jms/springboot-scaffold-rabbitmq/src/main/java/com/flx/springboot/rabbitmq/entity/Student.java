package com.flx.springboot.rabbitmq.entity;

/**
 * @Author Fenglixiong
 * @Create 2019.01.24 16:37
 * @Description
 **/
public class Student {

    private Long id;
    private String name;
    private String work;

    public Student() {
    }

    public Student(Long id, String name, String work) {
        this.id = id;
        this.name = name;
        this.work = work;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
