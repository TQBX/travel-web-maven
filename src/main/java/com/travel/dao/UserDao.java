package com.travel.dao;

import com.travel.domain.User;

import java.util.List;

/**
 * @auther Summerday
 */
public interface UserDao {

    List<User> findAll();
}
