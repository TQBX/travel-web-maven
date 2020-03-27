package com.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.domain.ResultInfo;
import com.travel.domain.User;
import com.travel.service.UserService;
import com.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author summerday
 *
 * UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    /**
     * 声明UserService的业务对象
     */
    private UserService service = new UserServiceImpl();
    /**
     * 声明User对象
     */
    private User user = new User();
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!checkCode(request,response)){
            return;
        }
        //获取数据
        Map map = request.getParameterMap();

        //封装对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册
        boolean flag = service.register(user);
        //响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名已存在!");
        }
        //将info对象序列化为json,返回客户端
        writeValue(info,response);
    }
    private boolean checkCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //验证码校验
        String check = request.getParameter("check");

        HttpSession session = request.getSession();
        String checkCodeServer = (String) session.getAttribute("checkCode");

        //保证验证码只能使用一次
        session.removeAttribute("checkCode");
        //验证码不相等
        if(checkCodeServer == null||!checkCodeServer.equalsIgnoreCase(check)){

            if("".equals(check)){
                info.setErrorMsg("验证码不能为空");
            }else {
                info.setErrorMsg("验证码错误");
            }
            info.setFlag(false);
            writeValue(info,response);
            return false;
        }
        return true;
    }
    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!checkCode(request,response)){
            return;
        }
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);

        //调用service查询
        User u = service.login(user);
        //判断用户对象是否存在
        if(u == null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断用户是否激活
        String activeCode = "Y";
        if(u!=null&&!activeCode.equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活,请激活");
        }
        //登录成功
        if(u!=null&&activeCode.equals(u.getStatus())){
            //登录成功标记
            request.getSession().setAttribute("user",u);

            //登陆成功
            info.setFlag(true);

        }
        //响应数据
        writeValue(info,response);
    }

    /**
     * 查询一个用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从session中获取登录用户
        User sessionUser = (User) request.getSession().getAttribute("user");

        //将user写回客户端
        writeValue(sessionUser,response);
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");

        if(code!=null){
            //调用service完成激活
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
            response.getWriter().write(msg);
        }
    }
}
