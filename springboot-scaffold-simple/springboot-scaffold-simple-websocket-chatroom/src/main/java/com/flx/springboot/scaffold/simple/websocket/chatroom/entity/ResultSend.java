package com.flx.springboot.scaffold.simple.websocket.chatroom.entity;

import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2018.11.21 11:13
 * @Description
 **/
@Data
public class ResultSend {

    int success = 0;
    int fail = 0;
    int total = 0;

    public ResultSend() {
    }

    public ResultSend(int success, int fail, int total) {
        this.success = success;
        this.fail = fail;
        this.total = total;
    }
}
