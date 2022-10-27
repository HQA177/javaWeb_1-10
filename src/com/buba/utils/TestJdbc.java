package com.buba.utils;

import com.buba.entity.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class TestJdbc {
    public static void main(String[] args) {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDateSource());
        //3.调用方法
//        String sql = "select count(*) from t_book";
//        Double count = template.queryForObject(sql, Double.class);
//        System.out.println(count);


//        String sql = "insert into t_order(order_id,create_time,order_price,order_count,order_status,user_id) value(?,?,?,?,?,?)";
//        int update = template.update(sql, "111", new Date(), 1, new BigDecimal(100), 0, 1);
//        System.out.println(update);

//        String sql = "insert into t_order_detail(book_name,book_count,price,order_id) value(?,?,?,?)";
//        int update = template.update(sql,"java",1,new BigDecimal(100),111);
//        System.out.println(update);

        String sql = "select * from t_order where user_id = ? limit ?,5";
        List<Order> query = template.query(sql, new BeanPropertyRowMapper<>(Order.class),1 , 0);
        System.out.println(query);
    }

}
