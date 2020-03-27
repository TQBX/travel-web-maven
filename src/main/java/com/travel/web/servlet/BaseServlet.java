package com.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.domain.ResultInfo;
import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Summerday
 */
public class BaseServlet extends HttpServlet {
    /**
     *  序列化json对象
     */
    public ObjectMapper mapper = new ObjectMapper();
    /**
     *  消息提示对象
     */
    public ResultInfo info = new ResultInfo();

    /**
     *  设置json数据返回的响应头
     */
    public final static String JSON_CONTENT_TYPE  = "application/json;charset=utf-8";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //完成方法的分发
        //获取请求路径
        //请求uri = /travel/user/add
        String uri = req.getRequestURI();

        //获取方法名称
        //方法名methodName = add
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);

        //获取方法对象Method(反射) 获取xxServlet的字节码对象==>利用xxServlet对象的getClass方法

        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将传入的对象序列化为json,并写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为json,返回给调用者
     * @param obj
     */
    public String writeValueAsString(Object obj,HttpServletResponse response) throws JsonProcessingException {
        response.setContentType(JSON_CONTENT_TYPE);
        return mapper.writeValueAsString(obj);
    }


}
