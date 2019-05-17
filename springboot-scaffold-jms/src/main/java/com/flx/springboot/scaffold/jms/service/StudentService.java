package com.flx.springboot.scaffold.jms.service;

import com.flx.springboot.scaffold.common.utils.json.JsonUtils;
import com.flx.springboot.scaffold.jms.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Fenglixiong
 * @Create 2019.01.24 16:39
 * @Description
 **/
@Slf4j
@Service
public class StudentService {

    public Long save(Student student){
        log.info("saveStudent successful:{}", JsonUtils.toJsonMsg(student));
        return 99L;
    }

}
