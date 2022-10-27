package com.buba.service.impl;

import com.buba.dao.Impl.OrderDaoImpl;
import com.buba.dao.OrderDao;
import com.buba.entity.Cart;
import com.buba.entity.Order;
import com.buba.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> queryOrder(Integer id, Integer pageNo) {
        return orderDao.queryOrder(id,pageNo);
    }

    @Override
    public int getOrderCount() {
        return orderDao.getOrderCount();
    }
}