package com.flx.springboot.scaffold.web.core.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.12.19 17:39
 * @Description 操作类型
 **/
public enum OperateTypeEnum {

    ADD(1,"新增"),
    UPDATE(2,"修改"),
    DELETE(3,"删除"),
    SELECT(4,"查询"),
    OTHER(5,"其他");

    private OperateTypeEnum(int type,String desc){
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
