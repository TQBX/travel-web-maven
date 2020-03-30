package com.travel.dao.impl;

import com.travel.dao.FavoriteDao;
import com.travel.domain.Favorite;
import com.travel.domain.Route;
import com.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.print.DocFlavor;
import java.util.ArrayList;
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
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public List<Favorite> findTopFavorite(int top) {

        List<Favorite> list = Collections.emptyList();
        try {
            String sql = "SELECT * FROM (SELECT rid,COUNT(rid) AS COUNT FROM tab_favorite GROUP BY rid ORDER BY COUNT(rid) DESC)AS aa LIMIT ?";
            list = template.query(sql,new BeanPropertyRowMapper<>(Favorite.class),top);

        }catch (Exception e){

        }
        return list;
    }

    @Override
    public List<Favorite> findTotal() {
        List<Favorite> list = Collections.emptyList();
        try {
            String sql = "SELECT * FROM (SELECT rid,COUNT(rid) AS COUNT FROM tab_favorite GROUP BY rid ORDER BY COUNT(rid) DESC)AS aa";
            list = template.query(sql,new BeanPropertyRowMapper<>(Favorite.class));

        }catch (Exception e){

        }
        return list;
    }

    @Override
    public List<Route> findRouteByRangePage(int start, int pageSize, String rname, int first, int last) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";

        String sql = "SELECT * FROM (SELECT * FROM (SELECT rid,COUNT(rid) AS COUNT FROM tab_favorite " +
                "GROUP BY rid ORDER BY COUNT(rid) DESC)AS aa)AS bb,tab_route t WHERE t.rid = bb.rid ";
        StringBuilder sb = new StringBuilder();
        //条件们
        List params = new ArrayList();
        //判断参数是否有值
        if(rname!=null&&rname.length()>0){
            sb.append("and t.rname like ?");
            params.add("%"+rname+"%");
        }
        if(first!=0){
            sb.append("and t.price > ? ");
            //添加?对应的值
            params.add(first);
        }
        if(last!=0){
            sb.append("and t.price < ? ");
            //添加?对应的值
            params.add(last);
        }
        //分页
        sb.append("limit ? , ? ");
        sql += sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }

    @Override
    public int findCountGroupByRid(int rid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";

        return template.queryForObject(sql,Integer.class,rid);

    }

    @Override
    public int findCountByRangeWithOutPage(String rname, int first, int last) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";

        String sql = "SELECT COUNT(*) FROM (SELECT * FROM (SELECT rid,COUNT(rid) AS COUNT FROM tab_favorite " +
                "GROUP BY rid ORDER BY COUNT(rid) DESC)AS aa)AS bb,tab_route t WHERE t.rid = bb.rid ";
        StringBuilder sb = new StringBuilder();

        //条件们
        List params = new ArrayList();
        //判断参数是否有值

        if(rname!=null&&rname.length()>0){

            sb.append("and t.rname like ?");
            params.add("%"+rname+"%");
        }
        if(first!=0){
            sb.append("and t.price > ? ");
            //添加?对应的值
            params.add(first);
        }
        if(last!=0){
            sb.append("and t.price < ? ");
            //添加?对应的值
            params.add(last);
        }
        //分页
        sql += sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }
}
