package com.fh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.commons.HttpRequestUtil;
import com.fh.model.ShopSearchParam;
import com.fh.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/testUser")
public class UserCon {

    @RequestMapping("/login")
    @ResponseBody
    Map<String, Object> login(User user){
        ObjectMapper obj =new ObjectMapper();
        ModelMap result=new ModelMap();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8092/user/login";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("code",str);
        return result;
    }
    @RequestMapping("/logins")
    @ResponseBody
    Map<String, Object> logins(User user){
        ObjectMapper obj =new ObjectMapper();
        ModelMap result=new ModelMap();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8092/user/logins";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("code",str);
        return result;
    }

}
