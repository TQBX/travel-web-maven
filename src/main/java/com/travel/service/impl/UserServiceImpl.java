package com.travel.service.impl;

import com.travel.dao.UserDao;
import com.travel.dao.impl.UserDaoImpl;
import com.travel.domain.User;
import com.travel.service.UserService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @auther Summerday
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> UserFindAll() {
        return userDao.findAll();
    }
}
