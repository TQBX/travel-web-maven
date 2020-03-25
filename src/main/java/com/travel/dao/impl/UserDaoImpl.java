package com.travel.dao.impl;

import com.travel.dao.UserDao;
import com.travel.domain.User;
import com.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @auther Summerday
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {

        String sql = "select * from user";

        List<User> Users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return Users;
    }
}
