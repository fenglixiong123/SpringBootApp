package com.flx.springboot.scaffold.mybatis.service;

import com.flx.springboot.scaffold.mybatis.model.Father;
import com.flx.springboot.scaffold.mybatis.model.Teacher;
import com.flx.springboot.scaffold.mybatis.model.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 17:57
 * @Description
 *
 * 主方法加事务则调用的方法都将加入这个事务中去
 * 1.publish调用两个方法AB，都有事务，publish无事务则不会生效
 *
 **/
@Slf4j
@Service
public class TransactionService {

    @Autowired
    private FatherService fatherService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private WorkerService workerService;

    @Transactional
    public void publish(){
        service();

        throw new RuntimeException("error");
    }

    public void service(){
        saveFather();
        saveWorker();
        saveTeacher();
        serviceC();
        serviceA();
        serviceB();
        saveWorker();
        saveFather();
        saveTeacher();
        throw new RuntimeException("error");
    }

    public void serviceA(){
        saveFather();
    }

    public void serviceB(){
        saveTeacher();
    }

    public void serviceC(){
        saveWorker();
    }

//    @Transactional
    public void saveFather(){
        Father father = new Father();
        father.setName("jack");
        father.setWork("basket");
        fatherService.save(father);
        log.info("saveFather success");
//        throw new RuntimeException("error");
    }

//    @Transactional
    public void saveTeacher(){
        Teacher teacher = new Teacher();
        teacher.setName("mary");
        teacher.setHomeFood("rou rou");
        teacher.setWorkFood("bai cai");
        teacherService.save(teacher);
        log.info("saveTeacher success");
//        throw new RuntimeException("error");
    }

    //    @Transactional
    private void saveWorker(){
        Worker worker = new Worker();
        worker.setName("tom");
        worker.setWorkContent("wood");
        workerService.save(worker);
        log.info("saveWorker success");
//        throw new RuntimeException("error");
    }

}
