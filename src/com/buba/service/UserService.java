package com.buba.service;

import com.buba.entity.User;

public interface UserService {
    // 注册用户,添加用户
    int addUserDao(User user);

    // 查询,通过用户名和密码在数据库查询是否存在该用户
    int login(String userName, String userPassword);

    // 查询,通过用户名在数据库查询是否存在该用户
    int findUserByName(String userName);
}
