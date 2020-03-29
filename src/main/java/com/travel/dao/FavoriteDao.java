package com.travel.dao;

import com.travel.domain.Favorite;

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

}
