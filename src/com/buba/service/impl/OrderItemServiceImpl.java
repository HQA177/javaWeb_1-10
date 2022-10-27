package com.buba.service.impl;

import com.buba.dao.Impl.OrderItemDaoImpl;
import com.buba.dao.OrderItemDao;
import com.buba.entity.OrderItem;
import com.buba.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public int addOrderItem(OrderItem orderItem) {
        return orderItemDao.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> detailsPage(Integer id) {
        return orderItemDao.detailsPage(id);
    }
}
