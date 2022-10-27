package com.buba.dao.Impl;

import com.buba.dao.OrderDao;
import com.buba.entity.Order;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate jdbc = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int createOrder(Order order) {
        String sql = "insert into t_order(order_number,order_price,order_count,order_status,user_id) value(?,?,?,?,?)";
        return jdbc.update(sql,order.getOrderNumber(),order.getOrderPrice(),order.getOrderCount(),order.getOrderStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrder(Integer id, Integer pageNo) {
        String sql = "select * from t_order where user_id = ? limit ?,5";
        List<Order> query = jdbc.query(sql, new BeanPropertyRowMapper<>(Order.class), id, (pageNo-1)*5);
        return query;
    }

    @Override
    public int getOrderCount() {
        String sql = "select count(*) from t_order";
        int date = jdbc.queryForObject(sql,Integer.class);
        return date;
    }
}
