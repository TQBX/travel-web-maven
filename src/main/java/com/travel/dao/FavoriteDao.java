package com.travel.dao;

import com.travel.domain.Favorite;
import com.travel.domain.Route;

import java.util.List;

/**
 * @author Summerday
 */
public interface FavoriteDao {

    /**
     * 根据rid和uid查询Favorite对象
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);

    /**
     * 根据uid查询总收藏数
     * @param uid
     * @return
     */
    int findTotalCountByUid(int uid);

    /**
     * 分页查询Favorite
     * @param uid
     * @param start
     * @param pageSize
     * @return
     */
    List<Favorite> findByPage(int uid, int start, int pageSize);

    /**
     * 找到收藏量top前几的route
     * @param top
     * @return
     */
    List<Favorite> findTopFavorite(int top);

    /**
     * 找到所有的Favorite(不重复)
     * @return
     */
    List<Favorite> findTotal();

    /**
     * 分页范围查询route
     * @param start
     * @param pageSize
     * @param rname
     * @param first
     * @param last
     * @return
     */
    List<Route> findRouteByRangePage(int start, int pageSize, String rname, int first, int last);

    /**
     * 查询对应rid路线的收藏次数
     * @param rid
     * @return
     */
    int findCountGroupByRid(int rid);

    /**
     * 不分页情况下的总数
     * @param rname
     * @param first
     * @param last
     * @return
     */
    int findCountByRangeWithOutPage(String rname, int first, int last);
}
