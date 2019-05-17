package com.flx.springboot.scaffold.web.core.enums;

public enum BizTypeEnum {

    SYSTEM(1,"系统日志"),
    USER(2,"用户日志"),
    OTHER(3,"其他日志");

    private BizTypeEnum(int type,String desc){
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
