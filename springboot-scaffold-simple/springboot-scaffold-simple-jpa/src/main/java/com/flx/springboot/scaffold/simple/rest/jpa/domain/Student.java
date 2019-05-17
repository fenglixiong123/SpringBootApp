package com.flx.springboot.scaffold.simple.rest.jpa.domain;

import com.flx.springboot.scaffold.simple.rest.jpa.base.BaseDomain;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:20
 * @Description
 **/
@Data
@Entity
@Table(name = "t_student")
public class Student extends BaseDomain {

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

}
