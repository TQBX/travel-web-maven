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
     */

    List<Route> findByPage(int cid, int start, int pageSize, String rname);
}
