package com.buba.service;

import com.buba.entity.Order;

import java.util.List;

public interface OrderService {

    // 创建订单
    int createOrder(Order order);

    // 查询用户订单
    List<Order> queryOrder(Integer id, Integer pageNo);

    // 查询用户的订单条数
    int getOrderCount();
}
