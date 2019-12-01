package com.fh.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.controller.CustomerBean;
import com.fh.dao.UserDao;
import com.fh.service.UserService;
import com.fh.model.User.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

   /* @Override
    public User login() {
        return userDao.login();
    }*/

    @Override
    public CustomerBean isRegister(String phone) {
        //判断手机号是否存在
        QueryWrapper<CustomerBean> queryWrapper = new QueryWrapper<CustomerBean>();
        queryWrapper.eq("phone", phone);
        CustomerBean customer= userDao.selectOne(queryWrapper);
        if(customer == null){
            customer =new CustomerBean();
            customer.setModifiedTime(new Date());
            customer.setUserStats(1);
            customer.setPhone(phone);
            customer.setCartId(UUID.randomUUID().toString().replace("-",""));
            userDao.insert(customer);
        }else{
            customer.setModifiedTime(new Date());
            userDao.updateById(customer);
        }
        return customer;
    }
}
