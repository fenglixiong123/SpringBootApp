package com.flx.springboot.scaffold.mybatis.common;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * @Author Fenglixiong
 * @Create 2019.01.22 20:48
 * @Description 返回Page参数
 **/
public class PageResult<V> {

    //("页码数")
    private final long pageNo;
    //("页面大小")
    private final long pageSize;
    //("总页数")
    private final long pageTotal;
    //("总记录数")
    private final long total;
    //("数据集")
    private final List<V> list;

    public PageResult(PageInfo<V> pageInfo){
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pageTotal = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }

    public PageResult(long pageNo, long pageSize, long total, List<V> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotal = getTotalPage(total,pageSize);
        this.total = total;
        this.list = list;
    }

    public PageResult(long pageNo, long pageSize, long pageTotal, long total, List<V> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.total = total;
        this.list = list;
    }

    public PageResult(long total, List<V> list) {
        this.pageNo = 1;
        this.pageSize = total;
        this.pageTotal = 1;
        this.total = total;
        this.list = list;
    }

    public PageResult(Page<?> page, List<V> list) {
        this.pageNo = page.getPageNum() - 1;
        this.pageSize = page.getPageSize();
        this.pageTotal = page.getPages();
        this.total = page.getTotal();
        this.list = list;
    }

    public PageResult(PageResult<?> page, List<V> list) {
        this.pageNo = page.getPageNo();
        this.pageSize = page.getPageSize();
        this.pageTotal = page.getPageTotal();
        this.total = page.getTotal();
        this.list = list;
    }

    private long getTotalPage(long total,long pageSize){
        return (total - 1) / pageSize + 1;
    }

    /**
     * Builder构造器
     *
     * @param datas
     * @param <V>
     *
     * @return
     */
    public static <V> Builder<V> builder(List<V> datas) {
        return new Builder<>(datas);
    }

    /**
     * 将Page转换为PageDTO
     *
     * @param page
     * @param <V>
     *
     * @return
     */
    public static <V> PageResult<V> of(Page<V> page) {
        Objects.requireNonNull(page, "common is null");
        return new PageResult<>(page.getPageNum() - 1, page.getPageSize(), page.getPages(), page.getTotal(),
                Optional.ofNullable(page.getResult()).orElse(Collections.emptyList()));
    }

    public static <V> PageResult<V> of(Page<?> page, List<V> list) {
        Objects.requireNonNull(page, "common is null");
        return new PageResult<V>(page, Optional.ofNullable(list).orElse(Collections.emptyList()));
    }

    public static <V> PageResult<V> of(PageResult<?> page, List<V> list) {
        Objects.requireNonNull(page, "common is null");
        return new PageResult<V>(page, Optional.ofNullable(list).orElse(Collections.emptyList()));
    }

    /**
     * 将Page转换为PageDTO，需提供转换器对列表数据进行转换
     *
     * @param page
     * @param converter
     * @param <T>
     * @param <V>
     *
     * @return
     */
    public static <T, V> PageResult<V> of(Page<T> page, Function<T, V> converter) {

        Objects.requireNonNull(page, "common is null");
        Objects.requireNonNull(converter, "converter is null");

        return new PageResult<>(page.getPageNum() - 1, page.getPageSize(), page.getPages(), page.getTotal(),
                Optional.ofNullable(page.getResult())
                        .map(content -> content.stream().map(converter).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()));
    }

    /**
     * 根据转换器构造PageDTO
     *
     * @param page
     * @param converter
     * @param <T>
     * @param <V>
     *
     * @return
     */
    public static <T, V> PageResult<V> of(PageResult<T> page, Function<T, V> converter) {
        Objects.requireNonNull(page, "common is null");
        Objects.requireNonNull(converter, "converter is null");
        return new PageResult<>(page.getPageNo(), page.getPageSize(), page.pageTotal, page.getTotal(),
                Optional.ofNullable(page.getList())
                        .map(content -> content.stream().map(converter).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()));
    }

    public final long getTotal() {
        return total;
    }

    public final List<V> getList() {
        return list;
    }

    public long getPageNo() {
        return pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getPageTotal() {
        return pageTotal;
    }


    @Override
    public String toString() {
        return "PageResult{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", total=" + total +
                ", list=" + list +
                '}';
    }

    /**
     * Builder构造器
     *
     * @param <V>
     */
    public static class Builder<V> {
        private long pageNo;
        private long pageSize;
        private long totalPage;
        private long totalRecords;
        private final List<V> datas;

        private Builder(List<V> datas) {
            Objects.requireNonNull(datas, "list is null");
            this.datas = datas;
            totalRecords = datas.size();
        }

        public final Builder<V> pageNo(long pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public final Builder<V> pageSize(long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public final Builder<V> totalPage(long totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public final Builder<V> totalRecords(long totalRecords) {
            this.totalRecords = totalRecords;
            return this;
        }

        public final PageResult<V> build() {
            return new PageResult<V>(pageNo, pageSize, totalPage, totalRecords, datas);
        }
    }

}
