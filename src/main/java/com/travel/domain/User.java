package com.travel.domain;

//import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @auther Summerday
 * User实体类
 */
public class User {
    //private static Logger logger = Logger.getLogger(User.class);
    private Integer id;
    private String username;
    private String password;
    private String nickname;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ']';
    }

    ////监听javabean再session域中的添加过程
    //@Override
    //public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
    //    logger.info("用户"+username+"已登录");
    //}
    ////监听javabean再session域中的移除过程
    //@Override
    //public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
    //    logger.info("用户"+username+"已注销");
    //}
}
