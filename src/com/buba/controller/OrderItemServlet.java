package com.buba.controller;

import com.buba.entity.OrderItem;
import com.buba.service.OrderItemService;
import com.buba.service.impl.OrderItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderItemServlet extends ViewBaseServlet{
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("orderItem").equals("detailsPage")){
            this.detailsPage(req,resp);
        }
        if (req.getParameter("orderItem").equals("orderRefund")){
            this.orderRefund(req,resp);
        }
    }

    public void detailsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String orderId = req.getParameter("orderId");
        session.setAttribute("orderId",orderId);
        List<OrderItem> orderItems = orderItemService.detailsPage(Integer.parseInt(orderId));
        System.out.println(orderItems);
        req.setAttribute("orderItems",orderItems);
        processTemplate("/pages/cart/details",req,resp);
    }

    public void orderRefund(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        orderItemService.orderItemRefund(Integer.parseInt(id));
        orderItemService.orderRefund(Integer.parseInt(id));
        resp.sendRedirect("orderHtml?order=getOrderCount");
    }
}
