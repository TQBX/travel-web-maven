package com.travel.dao;

import com.travel.domain.Route;

import java.util.List;

/**
 * @author Summerday
 */
public interface RouteDao {

    /**
     * 根据cid查询总记录数
     * @param cid cid
     * @param rname
     * @return totalCount
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */

    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据rid查询
     * @param rid
     * @return
     */
    Route findByRid(int rid);


    /**
     * 根据类别查询旅游
     * @param count
     * @param size
     * @return
     */
    //List<Route> findByCategory(String count, int size);

    /*    @Override
            public List<Route> findByCategory(String count, int size) {
                List<Route>list = new ArrayList<>();
                try {
                    String sql = "SELECT * FROM tab_route ORDER BY ? DESC LIMIT 0 , ?";
                    list = template.query(sql,new BeanPropertyRowMapper<>(Route.class),count,size);
                }catch (Exception e){

                }
                return list;
            }*/

    /**
     * 根据人气
     * @param size
     * @return
     */
    List<Route> findByCount(int size);

    /**
     * 根据主题
     * @param size
     * @return
     */
    List<Route> findByTheme(int size);

    /**
     * 根据日期
     * @param size
     * @return
     */
    List<Route> findByRdate(int size);
}
