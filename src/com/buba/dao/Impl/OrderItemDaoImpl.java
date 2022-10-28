package com.buba.dao.Impl;

import com.buba.dao.OrderItemDao;
import com.buba.entity.OrderItem;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    private JdbcTemplate jdbc = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_detail(book_id, book_name, book_count, price, order_id) value(?,?,?,?,?)";
        int update = jdbc.update(sql, orderItem.getBookId(), orderItem.getBookName(), orderItem.getBookCount(), orderItem.getPrice(), orderItem.getOrderId());
        return update;
    }

    @Override
    public List<OrderItem> detailsPage(Integer id) {
        String sql = "select * from t_order_detail where order_id = ?";
        List<OrderItem> query = jdbc.query(sql, new BeanPropertyRowMapper<>(OrderItem.class), id);
        return query;
    }

    @Override
    public int orderRefund(Integer id) {
        String sql = "delete from t_order where order_id = ?";
        int update = jdbc.update(sql, id);
        return update;
    }

    @Override
    public int orderItemRefund(Integer id) {
        String sql = "delete from t_order_detail where order_id = ?";
        int update = jdbc.update(sql, id);
        return update;
    }
}
