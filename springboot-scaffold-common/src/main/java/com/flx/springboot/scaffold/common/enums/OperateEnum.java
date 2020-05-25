package com.flx.springboot.scaffold.common.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 18:21
 * @Description
 **/
public enum OperateEnum {

    ADD(1,"新增"),
    UPDATE(2,"修改"),
    DELETE(3,"删除"),
    QUERY(4,"查询");

    private OperateEnum(int type, String desc) {
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
