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
    public int getOrderCount(Integer id) {
        return orderDao.getOrderCount(id);
    }

    @Override
    public List<Order> queryAllOrder(Integer pageNo) {
        return orderDao.queryAllOrder(pageNo);
    }

    @Override
    public int queryAllOrder() {
        return orderDao.queryAllOrder();
    }

    @Override
    public int queryOrderId(String num) {
        return orderDao.queryOrderId(num);
    }
}
