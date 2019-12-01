package com.fh.service;

import com.fh.controller.ResponseServer;
import com.fh.model.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    ResponseServer addCart(int productId, String userPhone);

    Map<String, Object> queryCart(String userPhone);

    void cartsShow(String productId, String userPhone);

    ResponseServer addCartNum(int productId, String userPhone);

    ResponseServer removeCartNum(int productId, String userPhone);

    ResponseServer deleteProduct(int productId, String userPhone);

    Cart queryCartId(int productId, String userPhone);

    List<Cart> queryCartIdAll(String userPhone);

    void changeContent(int productId, Integer count, String userPhone);
}
