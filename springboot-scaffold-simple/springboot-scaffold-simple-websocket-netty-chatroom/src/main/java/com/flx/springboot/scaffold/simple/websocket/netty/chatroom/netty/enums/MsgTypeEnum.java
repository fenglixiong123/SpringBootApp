package com.flx.springboot.scaffold.simple.websocket.netty.chatroom.netty.enums;

/**
 * 消息类型
 */
public enum MsgTypeEnum {

    text("文本"),   //文本消息
    image("图片"),  //图片消息
    voice("语音"),  //语音消息
    video("视频"),  //视频消息
    file("文件"),   //文件消息
    goods("商品");  //商品消息

    MsgTypeEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
