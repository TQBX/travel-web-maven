package com.travel.service.impl;

import com.travel.dao.FavoriteDao;
import com.travel.dao.RouteDao;
import com.travel.dao.RouteImgDao;
import com.travel.dao.SellerDao;
import com.travel.dao.impl.FavoriteDaoImpl;
import com.travel.dao.impl.RouteDaoImpl;
import com.travel.dao.impl.RouteImgDaoImpl;
import com.travel.dao.impl.SellerDaoImpl;
import com.travel.domain.PageBean;
import com.travel.domain.Route;
import com.travel.domain.RouteImg;
import com.travel.domain.Seller;
import com.travel.service.RouteService;

import java.util.List;

/**
 * @author Summerday
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {

        //封装pagebean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        //开始的记录数
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {

        //根据rid去route表中查询route对象
        Route route = routeDao.findByRid(Integer.parseInt(rid));

        //根据route的id查询图片信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //将routeImgList设置到route对象上
        route.setRouteImgList(routeImgList);

        //根据route的sid查询卖家信息
        Seller seller = sellerDao.findBySid(route.getSid());
        //将seller设置到route对象上
        route.setSeller(seller);

        //查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        //将count设置到route对象上
        route.setCount(count);

        return route;
    }


}
