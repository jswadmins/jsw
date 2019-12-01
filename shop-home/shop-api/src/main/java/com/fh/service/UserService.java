package com.fh.service;

import com.fh.model.User.User;

public interface UserService {
    User queryList(String userName);

    User queryPassword(String password);

    User queryUser(User user);
}
