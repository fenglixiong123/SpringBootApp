package com.flx.scaffold.jdbc.mybatis.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * t_worker
 * @author 
 */
public class Worker implements Serializable {
    private Long id;


    @NotNull(message = "name is null")
    private String name;

    @NotNull(message = "workContent is null")
    private String workContent;

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

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }
}