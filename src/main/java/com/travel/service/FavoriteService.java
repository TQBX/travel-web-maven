package com.travel.service;

import com.travel.domain.PageBean;
import com.travel.domain.Route;

import java.util.List;

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

    /**
     * 热门推荐
     * @param top
     * @return
     */
    List<Route> hotQuery(int top);

    /**
     * 收藏排行榜分页查询
     * @param currentPage
     * @param pageSize
     * @param rname
     * @param first
     * @param last
     * @return
     */
    PageBean<Route> pageFavoriteRank(int currentPage, int pageSize, String rname, int first, int last);
}
