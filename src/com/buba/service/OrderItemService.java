package com.buba.service;

import com.buba.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    // 查询用户的订单详情
    int addOrderItem(OrderItem orderItem);

    // 渲染订单详情页面
    List<OrderItem> detailsPage(Integer id);
}
