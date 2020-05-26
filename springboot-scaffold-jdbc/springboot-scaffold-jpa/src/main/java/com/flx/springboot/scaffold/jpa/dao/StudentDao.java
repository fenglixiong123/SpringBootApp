package com.flx.springboot.scaffold.jpa.dao;


import com.flx.springboot.scaffold.jpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Fenglixiong
 * @Create 2018.11.15 18:18
 * @Description
 **/
@Repository
public interface StudentDao extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {

    @Query("update Student s set s.hobby = :shobby where s.id = :sid")
    Integer updateStudentHobbyById(@Param("shobby") String hobby, @Param("sid") Long id);

}
