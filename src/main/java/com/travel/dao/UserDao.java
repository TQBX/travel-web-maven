package com.travel.dao;

import com.travel.domain.User;

import java.util.List;

/**
 * @author Summerday
 */
public interface UserDao {

    /**
     * 查找所有User
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户名查找User
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 存储用户
     * @param user
     */
    void UserSave(User user);

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    User findUserByCode(String code);

    /**
     * 更新用户状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);
}
