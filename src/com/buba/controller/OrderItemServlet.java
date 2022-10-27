package com.buba.controller;

import com.buba.entity.OrderItem;
import com.buba.service.OrderItemService;
import com.buba.service.impl.OrderItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderItemServlet extends ViewBaseServlet{
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("orderItem").equals("detailsPage")){
            this.detailsPage(req,resp);
        }
    }

    public void detailsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderItemService.detailsPage(Integer.parseInt(orderId));
        req.setAttribute("orderItems",orderItems);
        processTemplate("/pages/cart/details",req,resp);
    }
}
