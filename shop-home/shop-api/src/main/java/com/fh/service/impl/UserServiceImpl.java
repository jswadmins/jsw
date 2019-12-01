package com.fh.service.impl;

import com.fh.dao.UserDao;
import com.fh.model.User.User;
import com.fh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User queryList(String userName) {
        return userDao.queryName(userName);
    }

    @Override
    public User queryPassword(String password) {
        return userDao.queryPassword(password);
    }

    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }
}
