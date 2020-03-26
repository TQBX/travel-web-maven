package com.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.domain.ResultInfo;
import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @auther Summerday
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO check
        //验证码校验
        String check = request.getParameter("check");

        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //验证码不相等
        if(checkcode_server == null||!checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setErrorMsg("验证码错误");
            info.setFlag(false);
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(info);
            response.getWriter().write(s);
            return;
        }



        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用service查询
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        //判断用户对象是否存在
        if(u == null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断用户是否激活
        if(u!=null&&!"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活,请激活");
        }
        //登录成功
        if(u!=null&&"Y".equals(u.getStatus())){
            //登录成功标记
            request.getSession().setAttribute("user",u);

            //登陆成功
            info.setFlag(true);

        }
        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),info);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
