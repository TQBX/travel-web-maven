package com.travel.service;

import com.travel.domain.User;

import java.util.List;

/**
 * @author Summerday
 */
public interface UserService {

    List<User> UserFindAll();

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
