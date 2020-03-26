package com.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.domain.ResultInfo;
import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.Map;

/**
 * @auther Summerday
 */
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        //获取数据
        Map map = request.getParameterMap();

        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册
        UserService service = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        boolean flag = service.register(user);
        //响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }
        //将info对象序列化为json,返回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
