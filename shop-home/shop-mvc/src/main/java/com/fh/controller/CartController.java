package com.fh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.commons.HttpRequestUtil;
import com.fh.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping("/cart")
    @ResponseBody
    Map<String,Object> cart(Cart cart){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(cart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/cart";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/queryCart")
    @ResponseBody
    Map<String,Object> cart(){
        ModelMap result=new ModelMap();
        String url = "http://localhost:8093/cart/queryCart";
        String str = HttpRequestUtil.doGet(url,null);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/queryCartIdAll")
    @ResponseBody
    Map<String,Object> queryCartIdAll(){
        ModelMap result=new ModelMap();
        String url = "http://localhost:8093/cart/queryCartIdAll";
        String str = HttpRequestUtil.doGet(url,null);
        System.out.println(str);
        return result;
    }
    @RequestMapping("/queryCartId")
    @ResponseBody
    Map<String,Object> queryCartId(int productId){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(productId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/queryCartId";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/cartsShow")
    @ResponseBody
    Map<String,Object> cartsShow(int productId){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(productId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/cartsShow";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/changeContent")
    @ResponseBody
    Map<String,Object> changeContent(Cart cart){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(cart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/changeContent";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/removeCartNum")
    @ResponseBody
    Map<String,Object> removeCartNum(int productId){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(productId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/removeCartNum";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
    @RequestMapping("/deleteProduct")
    @ResponseBody
    Map<String,Object> deleteProduct(int productId){
        ModelMap result=new ModelMap();
        ObjectMapper obj =new ObjectMapper();
        String  objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(productId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8093/cart/deleteProduct";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        result.put("data",str);
        return result;
    }
}
