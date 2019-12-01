package com.fh.dao;

import com.fh.model.User.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User queryName(String userName);

    User queryPassword(String password);

    User queryUser(User user);
}
