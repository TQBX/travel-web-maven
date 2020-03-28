package com.travel.service.impl;

import com.travel.dao.CategoryDao;
import com.travel.dao.impl.CategoryDaoImpl;
import com.travel.domain.Category;
import com.travel.service.CategoryService;
import com.travel.utils.JedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Summerday
 */
public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //从redis中查询
        //获取jedis客户端
        Jedis jedis = JedisUtils.getJedis();

        //查询sortedSet的score(cid)和值(cname)
        Set<Tuple> tupleSetInRedis = jedis.zrangeWithScores("category", 0, -1);


        //用于存储mysql数据库中查询的list
        List<Category> categoryInDb = null;

        //判断查询的集合是否为空
        if(tupleSetInRedis == null||tupleSetInRedis.size() == 0){
            //如果为空,则证明是第一次访问
            //从数据库查询,并存入数据进redis
            System.out.println("从数据库中查询数据");
            categoryInDb = categoryDao.findAll();
            //将all存储到redis的category中
            for (int i = 0; i < categoryInDb.size(); i++) {
                jedis.zadd("category",categoryInDb.get(i).getCid(),categoryInDb.get(i).getCname());
            }
        }else {
            System.out.println("从缓存中读取数据");
            //如果不为空,将set中的数据存入list
            categoryInDb = new ArrayList<>();
            for (Tuple tuple : tupleSetInRedis) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                categoryInDb.add(category);
            }
        }
        return categoryInDb;

    }
}
