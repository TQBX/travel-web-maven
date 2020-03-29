package com.travel.dao.impl;

import com.travel.dao.FavoriteDao;
import com.travel.domain.Favorite;
import com.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Summerday
 */
public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (Exception e) {

        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }

    @Override
    public int findTotalCountByUid(int uid) {
        String sql = "select count(*) from tab_favorite where uid = ?";
        return template.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public List<Favorite> findByPage(int uid, int start, int pageSize) {
        List<Favorite> list = Collections.emptyList();
        try {
            String sql = "select * from tab_favorite where uid = ? limit ? , ?";
            list = template.query(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, start, pageSize);
        }catch (Exception e){

        }
        return list;
    }
}
