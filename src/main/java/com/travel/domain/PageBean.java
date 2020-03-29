package com.travel.domain;

import java.util.List;

/**
 * @author Summerday
 *
 * 分页pagebean对象
 */
public class PageBean<T>{

    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页显示的条数
     */
    private Integer pageSize;

    /**
     * 每页显示的数据集合
     */
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean[" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                ']';
    }
}
