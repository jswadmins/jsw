package com.fh.controller;

import com.fh.model.User.User;
import com.fh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    Map login(@RequestBody User user){
        Map map=new HashMap();
        User user1=userService.queryList(user.getUserName());
        User user2=userService.queryPassword(user.getPassword());
        User user3=userService.queryUser(user);
        if(user.getUserName()==""){
            map.put("code","-1");
            map.put("message","用户名不准为空");
        }
        if(user.getPassword()==""){
            map.put("code","-2");
            map.put("message","密码不准为空");
        }
        if(!user1.getUserName().equals(user.getUserName())){
            map.put("code","-3");
            map.put("message","用户名错误");
        }
        if(!user2.getPassword().equals(user.getPassword())){
            map.put("code","-4");
            map.put("message","密码错误");
        }
        if(user2.getPassword().equals(user.getPassword()) && user2.getPassword().equals(user.getPassword())){
            map.put("code","200");
            map.put("message","成功");
        }
        return map;
    }

 }
