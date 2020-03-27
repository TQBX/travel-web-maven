package com.travel.domain;
/**
 * @author Summerday
 * User实体类
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String sex;
    private String telephone;
    private String email;
    private String status;
    private String code;

    public User() {
    }

    /**
     * 含参构造
     * @param uid 用户id
     * @param username 用户名
     * @param password 密码
     * @param name 真实姓名
     * @param birthday 出生日期
     * @param sex 性别 男/女
     * @param telephone 手机号
     * @param email 邮箱
     * @param status 激活状态Y/N
     * @param code 激活码
     */
    public User(Integer uid, String username, String password, String name, String birthday, String sex, String telephone, String email, String status, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
        this.code = code;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
