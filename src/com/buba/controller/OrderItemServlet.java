package com.buba.controller;

import com.buba.entity.Book;
import com.buba.entity.OrderItem;
import com.buba.service.BookService;
import com.buba.service.OrderItemService;
import com.buba.service.impl.BookServletImpl;
import com.buba.service.impl.OrderItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemServlet extends ViewBaseServlet{
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private BookService bookService = new BookServletImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("orderItem").equals("detailsPage")){
            this.detailsPage(req,resp);
        }
        if (req.getParameter("orderItem").equals("orderRefund")){
            this.orderRefund(req,resp);
        }
    }

    // 我的订单查看详情
    public void detailsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String orderId = req.getParameter("orderId");
        session.setAttribute("orderId",orderId);
        int count = 0;
        BigDecimal bigDecimal = new BigDecimal(0);
        List<OrderItem> orderItems = orderItemService.detailsPage(Integer.parseInt(orderId));
        List<Book> books = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            Integer bookId = orderItem.getBookId();
            count += orderItem.getBookCount();
            bigDecimal = bigDecimal.add(orderItem.getPrice());
            Book book = bookService.skipEditBook(bookId);
            books.add(book);
        }
        System.out.println(orderItems);
        session.setAttribute("books",books);
        session.setAttribute("bookCount",count);
        session.setAttribute("price",bigDecimal);
        req.setAttribute("orderItems",orderItems);
        processTemplate("/pages/cart/details",req,resp);
    }

    // 订单退款
    public void orderRefund(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        orderItemService.orderItemRefund(Integer.parseInt(id));
        orderItemService.orderRefund(Integer.parseInt(id));
        resp.sendRedirect("orderHtml?order=getOrderCount");
    }
}
