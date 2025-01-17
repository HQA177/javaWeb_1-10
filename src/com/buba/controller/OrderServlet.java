package com.buba.controller;

import com.alibaba.druid.util.StringUtils;
import com.buba.entity.*;
import com.buba.service.CartItemService;
import com.buba.service.OrderItemService;
import com.buba.service.OrderService;
import com.buba.service.impl.CartItemServiceImpl;
import com.buba.service.impl.OrderItemServiceImpl;
import com.buba.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class OrderServlet extends ViewBaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("order").equals("createOrder")){
            this.createOrder(req,resp);
        }
        if (req.getParameter("order").equals("getOrderCount")){
            this.getOrderCount(req,resp);
            processTemplate("/pages/order/order",req,resp);
        }
        // 渲染后台管理所有订单
        if (req.getParameter("order").equals("queryAllOrder")){
            this.queryAllOrder(req,resp);
        }
        // 跳转后台管理订单详情
        if (req.getParameter("order").equals("viewDetails")){
            this.viewDetails(req,resp);
        }
        // 订单详情修改
        if (req.getParameter("order").equals("modifyState")){
            this.modifyState(req,resp);
        }
    }


    // 后台管理查询所有订单
    protected void queryAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int pageNo = 1;
        String pageNoStr = req.getParameter("orderPageNo");
        if (!StringUtils.isEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }
        List<Order> list = orderService.queryAllOrder(pageNo);
        // 总记录条数
        int orderCount = orderService.queryAllOrder();
        // 总页数
        int orderPageCount = (orderCount+5-1)/5;
//        int p = (int) pageCount;
        ArrayList<Object> orderLi = new ArrayList<>();
        for (int i = 0; i < orderPageCount; i++) {
            orderLi.add(i+1);
        }
        session.setAttribute("orderPageNo",pageNo);
        session.setAttribute("orderLi",orderLi);

        session.setAttribute("orderPageCount",orderPageCount);
        // 总数量取整
        session.setAttribute("orderCount",orderCount);
        req.setAttribute("orders",list);
        processTemplate("pages/manager/order_manager",req,resp);
    }

    // 创建用户订单
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("currUser");
        System.out.println("dayinUSER:"+user);
        // 随机生成的订单编号
        String num = testUid();
        session.setAttribute("num",num);
        orderService.createOrder(new Order(num,user.getCart().getTotalMoney(),user.getCart().getTotalCount(),0,user.getUserId()));
        Map<Integer, CartItem> cartItemMap = user.getCart().getCartItemMap();
        int i1 = orderService.queryOrderId(num);
        for (CartItem value : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(value.getBookId());
            orderItem.setBookName(value.getBook().getBookName());
            orderItem.setBookCount(value.getBookCount());
            orderItem.setPrice(value.getBook().getPrice().multiply(new BigDecimal(value.getBookCount())));
            orderItem.setOrderId(i1);
            int i = orderItemService.addOrderItem(orderItem);
        }
        // 加载购物车
        cartItemService.clearCart(user.getUserId());
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        processTemplate("/pages/cart/checkout",req,resp);
    }

    // 渲染用户订单界面
    public void getOrderCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderPageNo = 1;
        String pageNoStr = req.getParameter("orderPageNo");
        if (!StringUtils.isEmpty(pageNoStr)){
            orderPageNo = Integer.parseInt(pageNoStr);
        }
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("currUser");


        // 总记录条数
        int orderCount = orderService.getOrderCount(user.getUserId());
        // 总页数
        int orderPageCount = (orderCount+5-1)/5;
//        int p = (int) pageCount;
        ArrayList<Object> orderLi = new ArrayList<>();
        for (int i = 0; i < orderPageCount; i++) {
            orderLi.add(i+1);
        }
        session.setAttribute("orderPageNo",orderPageNo);
        session.setAttribute("orderLi",orderLi);

        session.setAttribute("orderPageCount",orderPageCount);
        // 总数量取整
        session.setAttribute("orderCount",orderCount);
        List<Order> orders = orderService.queryOrder(user.getUserId(), orderPageNo);
        req.setAttribute("orderList",orders);
    }

    // 后台管理查看详情渲染页面
    public void viewDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String num = req.getParameter("num");
        session.setAttribute("num",num);
        List<OrderItem> orderItems = orderItemService.detailsPage(Integer.parseInt(num));
        req.setAttribute("items",orderItems);
        processTemplate("pages/manager/order_details",req,resp);
    }

    // 后台管理修改订单状态
    public void modifyState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("num");
        System.out.println("num打印："+num);
        String status = req.getParameter("status");
        req.setAttribute("status",status);
        Order order = new Order();
        order.setOrderStatus(Integer.parseInt(status));
        orderService.modifyState(order,Integer.parseInt(num));
        this.viewDetails(req,resp);
    }

    //生成订单编号
    public static String testUid() {
        // 1.最大支持1-9个集群机器部署
        int machineId = 1;
        // 2.生成uuid的hashCode值1
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 3.有可能是负数
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        // 4.结果
        String value =  machineId + String.format("%015d", hashCodeV);
        return value;
    }
}
