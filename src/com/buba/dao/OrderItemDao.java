package com.buba.dao;

import com.buba.entity.Order;
import com.buba.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    // 查询用户的订单详情
    int addOrderItem(OrderItem orderItem);

    // 渲染订单详情页面
    List<OrderItem> detailsPage(Integer id);

    // 退款   删除order中的orderId
    int orderRefund(Integer id);

    // 退款   删除orderItem中的orderId
    int orderItemRefund(Integer id);

}
