package com.flx.scaffold.jdbc.mybatis.model;

import java.io.Serializable;

/**
 * t_teacher
 * @author 
 */
public class Teacher implements Serializable {
    private Long id;

    private String name;

    private String workFood;

    private String homeFood;

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

    public String getWorkFood() {
        return workFood;
    }

    public void setWorkFood(String workFood) {
        this.workFood = workFood;
    }

    public String getHomeFood() {
        return homeFood;
    }

    public void setHomeFood(String homeFood) {
        this.homeFood = homeFood;
    }
}