package com.travel.dao.impl;

import com.travel.dao.CategoryDao;
import com.travel.domain.Category;
import com.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author Summerday
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        List<Category> list = Collections.emptyList();
        try{
            String sql = "select * from tab_category";
            list = template.query(sql,new BeanPropertyRowMapper<>(Category.class));
        }catch (Exception e){
        }
        return list;
    }
}
