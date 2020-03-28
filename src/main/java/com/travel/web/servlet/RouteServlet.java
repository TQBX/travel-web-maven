package com.travel.web.servlet;

import com.travel.domain.PageBean;
import com.travel.domain.Route;
import com.travel.service.RouteService;
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
        String pageSizeStr = request.getParameter("pageSize");
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
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //调用service查询pagebean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize,rname);
        //将pagebean对象序列化未json返回
        writeValue(pb,response);


    }


}
