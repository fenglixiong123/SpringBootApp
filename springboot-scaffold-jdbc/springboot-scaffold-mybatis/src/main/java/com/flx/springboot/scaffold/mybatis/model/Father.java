package com.flx.springboot.scaffold.mybatis.model;

import java.io.Serializable;

/**
 * t_father
 * @author 
 */
public class Father implements Serializable {
    private Long id;

    private String name;

    private String work;

    private static final long serialVersionUID = 1L;

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