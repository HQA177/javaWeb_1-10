package com.buba.service.impl;

import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.User;
import com.buba.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int addUserDao(User user) {
        return userDao.addUserDao(user);
    }

    @Override
    public int login(String userName, String userPassword) {
        return userDao.login(userName,userPassword);
    }

    @Override
    public int findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }
}
