package com.flx.springboot.scaffold.common.utils.excel;

import lombok.Data;

/**
 * 批量新增错误码
 *
 * @author fanzhen
 * @date2018-09-26-10:56
 */
@Data
public class AddListResult {
    private AddType addType;
    //0失败，1成功
    private Integer addResult = 1;
    private String code;
    private String message;
    private Object data;

    public enum AddType {
        AddSuccess,
        AddFail,
        UpdateSuccess,
        UpdateFail,
        WarehouseIdError,
        TenantCodeError,
        HigherIdError,
        HigherCodeError,
        IllegalData,
        EvoConfigError,
        BasicServerError
    }

    /*private Boolean isSuccess(){
        if(this.addResult==0){
            return false;
        }else if(this.addResult==1){
            return true;
        }else {
            return false;
        }
    }

    private Boolean isNotSuccess(){
        if(this.addResult==0){
            return true;
        }else if(this.addResult==1){
            return false;
        }else {
            return true;
        }
    }*/
}
