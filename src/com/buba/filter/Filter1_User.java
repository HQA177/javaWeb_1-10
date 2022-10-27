package com.buba.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filter1_User implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        //无论是否登录过,都要放行的资源   登录页  登录校验Controller 和一些静态资源
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        if(requestURI.contains("index")|| requestURI.contains("/static/") || requestURI.contains("findBooks")){
            // 直接放行
            filterChain.doFilter(req,resp);
            // 后续代码不再执行
            return;
        }

        if(req.getParameter("jump") != null){
            // 登录界面
            if (req.getParameter("jump").equals("login")){
                filterChain.doFilter(req,resp);
                return;
            }
            // 注册界面
            if (req.getParameter("jump").equals("register")){
                filterChain.doFilter(req,resp);
                return;
            }
        }
        if (req.getParameter("user") != null){
            // 登录成功界面
            if (req.getParameter("user").equals("login_success")){
                filterChain.doFilter(req,resp);
                return;
            }

            // 注册成功界面
            if (req.getParameter("user").equals("register_success")){
                filterChain.doFilter(req,resp);
                return;
            }

            // 判断用户名存在否
            if (req.getParameter("user").equals("findUserByName")){
                filterChain.doFilter(req,resp);
                return;
            }
        }
        // 需要登录之后才能访问的资源,如果没登录,重定向到login.jsp上,提示用户进行登录
        HttpSession session = req.getSession();
        Object user = session.getAttribute("username");
        if(user != null){// 如果登录过 放行
            filterChain.doFilter(req,resp);
        }else{// 没有登录过,跳转至登录页
            resp.sendRedirect("jumpHtml?jump=login");
        }
    }

    @Override
    public void destroy() {

    }
}
