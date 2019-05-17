package com.flx.springboot.scaffold.logger.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 18:18
 * @Description bizType
 **/
public enum BizTypeEnum {

    HOUSEING(1,"发房"),
    MIDDLER(2,"中介"),
    USER(3,"用户");

    private BizTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private int type;

    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
