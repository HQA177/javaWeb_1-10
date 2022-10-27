package com.buba.dao;

import com.buba.entity.Order;

import java.util.List;

public interface OrderDao {
    // 创建订单
    int createOrder(Order order);

    // 查询用户订单分页
    List<Order> queryOrder(Integer id,Integer pageNo);

    // 查询用户的订单条数
    int getOrderCount();
}
