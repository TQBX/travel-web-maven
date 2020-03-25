package com.travel.web;

import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @auther Summerday
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<User> users = userService.UserFindAll();

        request.setAttribute("users",users);

        request.getRequestDispatcher("/hello.jsp").forward(request,response);
    }
}
