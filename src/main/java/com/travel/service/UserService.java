package com.travel.service;

import com.travel.domain.User;

import java.util.List;

/**
 * @auther Summerday
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

    User login(User user);
}
