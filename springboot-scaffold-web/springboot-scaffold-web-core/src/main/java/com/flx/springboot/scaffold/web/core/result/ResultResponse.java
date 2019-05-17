package com.flx.springboot.scaffold.web.core.result;

import com.flx.springboot.scaffold.web.core.enums.ErrorMsgEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

/**
 * @Author Fenglixiong
 * @Create 2018.11.09 23:23
 * @Description
 **/
@Data
public class ResultResponse<E> {


    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    /**
     * 返回状态
     */
    private Boolean success;

    /**
     * 返回代码
     */
    private int code;

    /**
     * 返回错误信息
     */
    private String msg;

    /**
     * 返回结果
     */
    private E data;


    public ResultResponse(){

    }

    private ResultResponse(Boolean success, int code, String msg, E data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultResponse<E> success(int code,String message,E data) {
        this.success = true;
        this.code = code;
        this.msg = message;
        this.data = data;
        return this;
    }

    public ResultResponse<E> fail(int code,String message,E data) {
        this.success = false;
        this.code = code;
        this.msg = message;
        this.data = data;
        return this;
    }

    public ResultResponse<E> success(E data){
        return success(ErrorMsgEnum.SUCCESS.getCode(),ErrorMsgEnum.SUCCESS.getMsg(),data);
    }

    public ResultResponse<E> success(String message,E data){
        return success(ErrorMsgEnum.SUCCESS.getCode(),message,data);
    }

    public ResultResponse<E> fail(String message) {
        return fail(ErrorMsgEnum.SYS_ERROR.getCode(),message,null);
    }

    public ResultResponse<E> fail(String message,E data) {
        return fail(ErrorMsgEnum.SYS_ERROR.getCode(),message,data);
    }

    public ResultResponse<E> fail(Integer code,String message) {
        return fail(code,message,null);
    }


    public static <E> ResultResponse<E> ok(){
        return new ResultResponse<>(true,ErrorMsgEnum.SUCCESS.getCode(),ErrorMsgEnum.SUCCESS.getMsg(),null);
    }
    public static <E> ResultResponse<E> ok(E data){
        return new ResultResponse<>(true,ErrorMsgEnum.SUCCESS.getCode(),ErrorMsgEnum.SUCCESS.getMsg(),data);
    }
    public static ResultResponse<String> error() {
        return new ResultResponse<>(false,ErrorMsgEnum.SYS_ERROR.getCode(),ErrorMsgEnum.SYS_ERROR.getMsg(),ErrorMsgEnum.SYS_ERROR.getUserMsg());
    }
    public static ResultResponse<String> error(String message) {
        return new ResultResponse<>(false,ErrorMsgEnum.SYS_ERROR.getCode(),message,ErrorMsgEnum.SYS_ERROR.getUserMsg());
    }
    public static ResultResponse<String> error(ErrorMsgEnum errorMsgEnum) {
        return new ResultResponse<>(false,errorMsgEnum.getCode(),errorMsgEnum.getMsg(),errorMsgEnum.getUserMsg());
    }

    public static ResultResponse<String> error(ErrorMsgEnum errorMsgEnum,String userMsg) {
        return new ResultResponse<>(false,errorMsgEnum.getCode(),errorMsgEnum.getMsg(),userMsg);
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }
}
