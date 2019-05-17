package com.flx.springboot.scaffold.logger.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author Fenglixiong
 * @Create 2019.01.03 18:30
 * @Description 循环线程一直读取队列的内容进行
 **/
@Slf4j
@Component
public class QueueTask implements InitializingBean {

    private int count = 1;

    private void logTask() {
        log.info("log task ready ...");
        Thread logThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        log.info("Thread doing work ...[{}]",count++);
                        Thread.sleep(20000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        logThread.start();
        log.info("log task run ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logTask();
    }
}
