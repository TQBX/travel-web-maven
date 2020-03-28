package com.travel.dao.impl;

import com.travel.dao.RouteDao;
import com.travel.domain.Route;
import com.travel.utils.JDBCUtils;
import com.travel.utils.JedisUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Summerday
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";

        //返回一个值,用forObject
        return template.queryForObject(sql,Integer.class,cid);

    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid,start,pageSize);
    }
}
