package com.travel.service;

import com.travel.domain.PageBean;
import com.travel.domain.Route;

import java.util.List;

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

    /**
     * 根据人气查询
     * @param size
     * @return
     */
    List<Route> findCount(int size);

    /**
     * 根据日期查询
     * @param size
     * @return
     */
    List<Route> findDate(int size);

    /**
     * 根据主题查询
     * @param size
     * @return
     */
    List<Route> findTheme(int size);

}
