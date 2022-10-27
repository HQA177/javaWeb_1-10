package com.buba.dao.Impl;

import com.buba.dao.UserDao;
import com.buba.entity.Book;
import com.buba.entity.User;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:39
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbc = new JdbcTemplate(JDBCUtils.getDateSource());
    // 注册
    @Override
    public int addUserDao(User user) {
        String sql = "insert into t_user (user_name,user_password,user_email) values(?,?,?)";
        int data = jdbc.update(sql,user.getUserName(),user.getUserPassword(),user.getEmail());
        return data;
    }

    // 登录
    @Override
    public int login(String userName, String userPassword) {
        String sql = "select count(*) from t_user where user_name = ? and user_password = ?";
        return jdbc.queryForObject(sql,Integer.class,userName,userPassword);
    }

    // 第二种登录
    @Override
    public User loginPro(String userName, String userPassword) {
        String sql = "select * from t_user where user_name = ? and user_password = ?";
        return jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),userName,userPassword);
//         data;
    }

    @Override
    public int findUserByName(String userName) {
        String sql = "select count(*) from t_user where user_name = ?";
        int data = jdbc.queryForObject(sql,Integer.class,userName);
        return data;
    }
}
