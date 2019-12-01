package com.fh.service;

import com.fh.controller.CustomerBean;
import com.fh.model.User.User;

public interface UserService {
   // User login();

    CustomerBean isRegister(String phone);
}
