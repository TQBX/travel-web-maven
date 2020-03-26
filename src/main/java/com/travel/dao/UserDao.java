package com.travel.dao;

import com.travel.domain.User;

import java.util.List;

/**
 * @auther Summerday
 */
public interface UserDao {

    List<User> findAll();

    User findUserByUsername(String username);

    void UserSave(User user);

    User findUserByCode(String code);

    void updateStatus(User user);

    User findUserByUsernameAndPassword(String username, String password);
}
