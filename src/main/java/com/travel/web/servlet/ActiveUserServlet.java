package com.travel.web.servlet;

import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Summerday
 */
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");

        if(code!=null){
            //调用service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            String msg = null;
            //判断标记
            if(flag){
                //激活成功
                msg = "激活成功,请<a href='login.html'>登录</a>";
            }else {
                //激活失败
                msg = "激活失败,请联系管理员!";
            }
            //TODO response setContentType
            response.getWriter().write(msg);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
