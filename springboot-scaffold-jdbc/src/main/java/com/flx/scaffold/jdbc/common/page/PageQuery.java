package com.flx.scaffold.jdbc.common.page;

import org.hibernate.validator.constraints.Range;

/**
 * @Author Fenglixiong
 * @Create 2019.01.22 20:55
 * @Description 参数查询
 **/
public class PageQuery<V> {

    @Range(min = 1, max = Integer.MAX_VALUE, message = "page.pageNo.illegal")
    private Integer page = 1;

    @Range(min = 1, max = 50, message = "page.pageSize.illegal")
    private Integer pageSize = 10;

    //排序字段
    private String order = "createTime";

    //排序顺序
    private String orderDesc = "desc";

    //查询参数
    private V entity;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public V getEntity() {
        return entity;
    }

    public void setEntity(V entity) {
        this.entity = entity;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", entity=" + entity +
                '}';
    }

}
