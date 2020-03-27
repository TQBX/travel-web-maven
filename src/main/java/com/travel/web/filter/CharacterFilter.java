package com.travel.web.filter;

import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Summerday
 * <p>
 * 全站乱码处理
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {
    /**
     * request请求编码
     */
    private static final String REQUEST_TYPE = "utf-8";

    /**
     * response响应乱码
     */
    private static final String RESPONSE_TYPE = "text/html;charset=utf-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取请求方法
        String method = request.getMethod();

        //解决post请求中文数据乱码问题
        String post = "post";
        if (post.equalsIgnoreCase(method)) {
            request.setCharacterEncoding(REQUEST_TYPE);
        }

        //处理响应乱码
        response.setContentType(RESPONSE_TYPE);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
