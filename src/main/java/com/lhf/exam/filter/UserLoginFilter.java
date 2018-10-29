package com.lhf.exam.filter;


import com.lhf.exam.pojo.AccessRight;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserLoginFilter init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (httpServletRequest.getRequestURI().contains("detail") ||httpServletRequest.getRequestURI().equals("/")||httpServletRequest.getRequestURI().equals("/WebServlet") || httpServletRequest.getRequestURI().equals("/Logout") || httpServletRequest.getRequestURI().equals("/LoginServlet")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        List<AccessRight> rights = (List<AccessRight>) httpServletRequest.getSession().getAttribute("rights");
        if (rights!=null){
        for (AccessRight right : rights) {
            if (right.getRighturi().equals(httpServletRequest.getRequestURI().substring(1))) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }
        }
        httpServletRequest.getRequestDispatcher("WEB-INF/index.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
