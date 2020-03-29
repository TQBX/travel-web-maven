package com.travel.service;

import com.travel.domain.PageBean;
import com.travel.domain.Route;

/**
 * @author Summerday
 */
public interface FavoriteService {

    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);

    /**
     * 分页查询我的收藏
     * @param uid
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Route> pageFavorite(int uid, int currentPage, int pageSize);
}
