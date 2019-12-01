package com.fh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.Cart;
import com.fh.service.CartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders="NOLONGIN")
public class CartController {

    @Resource
    private CartService cartService;
    @Resource
    private RedisTemplate redisTemplate;

    @PutMapping("/cart")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer addCart(@RequestBody Cart cart, HttpServletRequest request) {
        String userPhone = (String) request.getAttribute("phone");
        userPhone = "15554028557";
        return cartService.addCart(cart.getProductId(), userPhone);
    }

    @GetMapping("/queryCart")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer queryCart() {
        String userPhone = "15554028557";
        Map<String, Object> list = cartService.queryCart(userPhone);
        return ResponseServer.success(list);
    }
    @GetMapping("/queryCartIdAll")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer queryCartIdAll() {
        String userPhone = "15554028557";
        List<Cart> list = cartService.queryCartIdAll(userPhone);
        return ResponseServer.success(list);
    }
    @PutMapping("/queryCartId")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer queryCartId(@RequestBody int productId) {
        String userPhone = "15554028557";
        Cart list = cartService.queryCartId(productId, userPhone);
        return ResponseServer.success(list);
    }
    @PutMapping("/cartsShow")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer cartsShow(@RequestBody int productId) {
        String userPhone = "15554028557";
        return cartService.addCartNum(productId, userPhone);
    }
    @PutMapping("/removeCartNum")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer removeCartNum(@RequestBody int productId) {
        String userPhone = "15554028557";
        return cartService.removeCartNum(productId, userPhone);
    }
    @PutMapping("/deleteProduct")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer deleteProduct(@RequestBody int productId) {
        String userPhone = "15554028557";
        return cartService.deleteProduct(productId, userPhone);
    }
    @PutMapping("/changeContent")
    @ResponseBody
    @LoginAnnotation
    public ResponseServer changeContent(@RequestBody Cart cart) {
        String userPhone = "15554028557";
        cartService.changeContent(cart.getProductId(), cart.getCount(), userPhone);
        return ResponseServer.success();
    }
}