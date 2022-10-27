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

/**
 * Author:SmallTiger
 * Date:2022-10-12
 * Time:11:30
 */
public class JumpHtmlServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 登录
        if(req.getParameter("jump").equals("login")){
            processTemplate("/pages/user/login",req,resp);
        }
        // 注册
        if(req.getParameter("jump").equals("register")){
            processTemplate("/pages/user/register",req,resp);
        }
        // 购物车
        if(req.getParameter("jump").equals("cart")){
            processTemplate("/pages/cart/cart",req,resp);
        }
        // 图书订单管理
        if(req.getParameter("jump").equals("order_manager")){
            processTemplate("/pages/manager/order_manager",req,resp);
        }
        // 我的订单
//        if(req.getParameter("jump").equals("order")){
//            processTemplate("/pages/order/order",req,resp);
//        }
}
}
