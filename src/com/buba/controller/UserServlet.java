package com.buba.controller;

import com.buba.entity.User;
import com.buba.service.UserService;
import com.buba.service.impl.UserServiceImpl;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends ViewBaseServlet{
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 请求转发跳转到 /WEB-INF/view/ 路径.html

        // 判断跳转路径
        // 注销
        if(req.getParameter("user").equals("logout")){
            HttpSession session = req.getSession();
            session.setAttribute("username",null);
            req.getRequestDispatcher("index").forward(req,resp);
        }
        // 登录成功
        if(req.getParameter("user").equals("login_success")){
            this.login(req,resp);
        }
        // 注册成功
        if(req.getParameter("user").equals("register_success")){
            this.register(req,resp);
        }
        // 注册时判断用户名是否存在
        if (req.getParameter("user").equals("findUserByName")){
            this.findUserByName(req,resp);
        }
    }


    // 登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("用户名："+username);
        int i = userService.login(username,(DigestUtils.md5DigestAsHex((password).getBytes())));
        System.out.println("密码："+password);
        System.out.println(i);
        if (i == 1){
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            // 内部转发  一次请求和响应
//            req.getRequestDispatcher("index").forward(req,resp);
            // 重定向，两次请求和响应
            resp.sendRedirect("index");
//            processTemplate("index",req,resp);
        }else {
            resp.getWriter().write(""+i);
//            processTemplate("/pages/user/login",req,resp);
        }
    }

    // 注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        userService.addUserDao(new User(name,(DigestUtils.md5DigestAsHex((password).getBytes())),email));
        HttpSession session = req.getSession();
        session.setAttribute("username",name);
//        this.findUserByName(req,resp);
//        processTemplate("/pages/user/register_success",req,resp);
        processTemplate("/pages/user/login",req,resp);
//        processTemplate("index",req,resp);
    }

    // 查询是否存在用户名
    public void findUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // username获取的数据是注册页面 axios url的数据
        String username = req.getParameter("name");
        int i = userService.findUserByName(username);
        if (i == 1){
            resp.getWriter().write(""+i);
        }
    }
}
