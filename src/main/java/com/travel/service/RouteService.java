package com.travel.service;

import com.travel.domain.PageBean;
import com.travel.domain.Route;

/**
 * @author Summerday
 *
 * 线路service
 */
public interface RouteService {

    /**
     * 根据类别分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询route
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
