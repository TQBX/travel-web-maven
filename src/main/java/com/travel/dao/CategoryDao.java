package com.travel.dao;

import com.travel.domain.Category;

import java.util.List;

/**
 * @author Summerday
 */
public interface CategoryDao {

    /**
     * 查询所有
     * @return
     */
    List<Category> findAll();
}
