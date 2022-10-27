package com.buba.controller;

import com.buba.entity.Book;
import com.buba.entity.Cart;
import com.buba.entity.CartItem;
import com.buba.entity.User;
import com.buba.service.CartItemService;
import com.buba.service.impl.CartItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartServlet extends ViewBaseServlet{
    private CartItemService cartItemService = new CartItemServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 购物车添加图书
        if (req.getParameter("cart").equals("addCart")){
                this.addCart(req,resp);
        }
        // 购物车删除图书
        if (req.getParameter("cart").equals("delCart")){
            this.delCart(req,resp);
        }
        // 清空购物车图书
        if (req.getParameter("cart").equals("clearCart")){
            this.clearCart(req,resp);
        }
        // 清空购物车图书
        if (req.getParameter("cart").equals("updateCartItem")){
            this.updateCartItem(req,resp);
        }

        // 加载当前用户的购物车信息
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        processTemplate("index",req,resp);
    }

    // 添加购物车图书项
    public void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currUser");
        String bookId = req.getParameter("bookId");
        CartItem cartItem = new CartItem(new Book(Integer.parseInt(bookId)),1,user);
        // 将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());
        resp.sendRedirect("index");
    }

    // 删除购物车图书项
    public void delCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = cartItemService.delCart(Integer.parseInt(id));
        resp.sendRedirect("/jumpHtml?jump=cart");
    }

    // 清空购物车图书项
    public void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = cartItemService.clearCart(Integer.parseInt(id));
        resp.sendRedirect("/jumpHtml?jump=cart");
    }


    public void updateCartItem(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        String id = req.getParameter("id");
        String bookCount = req.getParameter("bookCount");
        CartItem cartItem = new CartItem(Integer.parseInt(id),Integer.parseInt(bookCount));
        cartItemService.updateCartItem(cartItem);
        resp.sendRedirect("/jumpHtml?jump=cart");
    }
}
