package com.travel.service.impl;

import com.travel.dao.RouteDao;
import com.travel.dao.impl.RouteDaoImpl;
import com.travel.domain.PageBean;
import com.travel.domain.Route;
import com.travel.service.RouteService;

import java.util.List;

/**
 * @author Summerday
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

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
}
