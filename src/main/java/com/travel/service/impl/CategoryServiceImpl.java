package com.travel.service.impl;

import com.travel.dao.CategoryDao;
import com.travel.dao.impl.CategoryDaoImpl;
import com.travel.domain.Category;
import com.travel.service.CategoryService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Summerday
 */
public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //从redis中查询
        //获取jedis客户端


        //判断查询的集合是否为空

        //如果为空,则证明是第一次访问
        //从数据库查询,并存入数据进redis

        //如果不为空,直接返回



        return categoryDao.findAll();

    }
}
