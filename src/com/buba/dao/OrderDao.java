package com.buba.dao;

import com.buba.entity.Order;

import java.util.List;

public interface OrderDao {
    // 创建订单
    int createOrder(Order order);

    // 查询用户订单分页
    List<Order> queryOrder(Integer id,Integer pageNo);

    // 查询用户的订单条数
    int getOrderCount(Integer id);

    // 后台管理查询所有订单
    List<Order> queryAllOrder(Integer pageNo);

    // 查询后台订单总记录条数
    int queryAllOrder();

    // 通过 number 查询 order_id
    int queryOrderId(String num);

    // 后台管理修改订单状态
    int modifyState(Order order, Integer id);

}
