package com.travel.domain;

import java.io.Serializable;

/**
 * @author summerday
 * 收藏实体类
 */
public class Favorite implements Serializable {
    /**
     * 旅游线路对象
     */
    private Integer rid;
    /**
     * 收藏时间
     */
    private String date;
    /**
     * 所属用户
     */
    private Integer uid;

    /**
     * 无参构造方法
     */
    public Favorite() {
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "rid=" + rid +
                ", date='" + date + '\'' +
                ", uid=" + uid +
                '}';
    }
}
