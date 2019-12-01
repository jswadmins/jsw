package com.fh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.commons.HttpClientUtils;
import com.fh.commons.HttpRequestUtil;
import com.fh.model.PageBean;
import com.fh.model.Product;
import com.fh.model.ShopSearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/test")
public class ShopCon {

    @RequestMapping("/shopQuery")
    @ResponseBody
    Map shopQuery(){
        ModelMap result=new ModelMap();
        String url = "http://localhost:8090/shop/queryList";
        //String str = HttpClientUtils.doGet(url,null);
        String str = HttpRequestUtil.doGet(url,null);
        System.out.println(str);
        result.put("a",str);
        result.put("code","200");
        return result;
    }
    @RequestMapping("/queryList")
    @ResponseBody
    String queryList(ShopSearchParam shopSearchParam){
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(shopSearchParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8090/shop/queryLists";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        return str;
    }
    @RequestMapping("/bbb")
    String bbb(){
        return "brandList";
    }
    @RequestMapping("/aaa")
    String aaa(){
        return "index";
    }


}
