package com.flx.springboot.scaffold.logger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Fenglixiong
 * @Create 2018.11.29 14:40
 * @Description
 **/
@Data
@Entity
@Table(name = "t_biz_log")
public class BizLogger {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //业务ID号
    @Column(name = "biz_id")
    private String bizId;

    //日志类型
    @Column(name = "biz_type")
    private Integer bizType;

    //类路径
    @Column(name = "class_path")
    private String classPath;

    //调用方法
    @Column(name = "method_name")
    private String methodName;

    //操作类型
    @Column(name = "operate_type")
    private Integer operateType;

    //请求体
    @Column(name = "param_json",columnDefinition = "text")
    private String paramJson;

    //返回结果
    @Column(name = "result_json")
    private String resultJson;

    //操作人ID
    @Column(name = "user_id")
    private String userId;

    //操作人姓名
    @Column(name = "user_name")
    private String userName;

    //方法说明
    @Column(name = "remark")
    private String remark;

    //检索字段一
    @Column(name = "search_one")
    private String searchOne;

    //检索字段二
    @Column(name = "search_two")
    private String searchTwo;

    //检索字段三
    @Column(name = "search_three")
    private String searchThree;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    public BizLogger() {
    }

    public BizLogger(String bizId,String classPath, String methodName) {
        this.bizId = bizId;
        this.classPath = classPath;
        this.methodName = methodName;
    }
}
