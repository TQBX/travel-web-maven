package com.travel.web.servlet;

import com.travel.domain.Favorite;
import com.travel.domain.PageBean;
import com.travel.domain.Route;
import com.travel.domain.User;
import com.travel.service.FavoriteService;
import com.travel.service.RouteService;
import com.travel.service.impl.FavoriteServiceImpl;
import com.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Summerday
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service= new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        //String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");


        //用户输入的查询rname
        String rname = request.getParameter("rname");

        rname = new String(rname.getBytes("iso8859-1"),"utf-8");

        int cid = 0;
        String nullStr = "null";
        //处理参数
        if(cidStr!=null&&cidStr.length()>0&& !nullStr.equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1;
        //处理参数
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 10;
        //处理参数
        //if(pageSizeStr!=null&&pageSizeStr.length()>0){
        //    pageSize = Integer.parseInt(pageSizeStr);
        //}

        //调用service查询pagebean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize,rname);
        //将pagebean对象序列化未json返回
        writeValue(pb,response);

    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收id

        String rid = request.getParameter("rid");
        //调用service查询route对象

        Route route = service.findOne(rid);

        //转为json,返回客户端
        writeValue(route,response);

    }

    /**
     * 判断当前登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取线路id
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if(user == null){
            //用户未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        //调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //将flag写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取线路
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if(user == null){
            //用户未登录
            return;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        //调用service完成添加
        favoriteService.add(rid,uid);

    }

    public void pageFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户的uid和currentpage
        String uidStr = request.getParameter("uid");
        String currentPageStr = request.getParameter("currentPage");
        int uid = Integer.parseInt(uidStr);
        int currentPage = 1;
        //处理参数
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 4;
        PageBean<Route> pb = favoriteService.pageFavorite(uid,currentPage,pageSize);
        writeValue(pb,response);


    }

}
