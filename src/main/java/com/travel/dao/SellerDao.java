package com.travel.dao;

import com.travel.domain.RouteImg;
import com.travel.domain.Seller;

import java.util.List;

/**
 * @author Summerday
 */
public interface SellerDao {

    /**
     * 根据route的sid找到seller信息
     * @param sid
     * @return
     */
    Seller findBySid(int sid);
}
