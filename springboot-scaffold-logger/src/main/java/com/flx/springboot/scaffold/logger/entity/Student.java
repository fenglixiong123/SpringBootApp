package com.flx.springboot.scaffold.logger.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:20
 * @Description
 **/
@Data
@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 4,max = 10,message = "student.name.length.4~10")
    @NotNull(message = "student.name.is.notnull")
    @Column(name = "name")
    private String name;

    @NotNull(message = "student.age.is.notnull")
    @Range(min = 19,max = 33,message = "student.age.range.19~33")
    @Column(name = "age")
    private Integer age;

    @NotNull(message = "student.hobby.is.notnull")
    @Column(name = "hobby")
    private String hobby;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
