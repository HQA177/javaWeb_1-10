package com.buba.dao;


import com.buba.entity.Book;
import com.buba.entity.User;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:33
 */
public interface UserDao {
    // 注册用户,添加用户
    int addUserDao(User user);

    // 查询,通过用户名和密码在数据库查询是否存在该用户
    int login(String userName, String userPassword);

    // 第二种登录方式
    User loginPro(String userName, String userPassword);

    // 查询,通过用户名在数据库查询是否存在该用户
    int findUserByName(String userName);

}
