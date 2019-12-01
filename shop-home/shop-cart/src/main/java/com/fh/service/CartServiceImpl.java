package com.fh.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.controller.HttpConnection;
import com.fh.controller.ResponseServer;
import com.fh.model.Cart;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ResponseServer addCart(int productId, String userPhone) {
        //获取购物车id
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        //根据商品id查询商品信息
        String url = "http://localhost:8090/shop/" + productId;
        String result = HttpConnection.doGet(url);
        JSONObject jsonObject= JSON.parseObject(result);
        System.out.println(jsonObject.get("data"));
        JSONObject productData=JSON.parseObject(jsonObject.get("data").toString());

        //讲数据加入购物车实体bean中
        Cart cartBean=new Cart();
        cartBean.setProductId(productData.getInteger("shopId"));
        cartBean.setProductName(productData.getString("shopName"));
        cartBean.setMainImg(productData.getString("imgPath"));
        cartBean.setPrice(productData.getBigDecimal("price"));
        //判断商品是否存在购物车
        if(redisTemplate.opsForHash().hasKey(cartId,productId)){
            Cart cart= (Cart) redisTemplate.opsForHash().get(cartId,productId);
            cartBean.setCount(cart.getCount()+1);
        }else{
            cartBean.setCount(1);
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal count = new BigDecimal(cartBean.getCount());
        BigDecimal subtotal = bigDecimal.add(cartBean.getPrice()).multiply(count);
        cartBean.setSubtotal(subtotal);
        cartBean.setChecked(true);
        //加入到redis
        redisTemplate.opsForHash().put(cartId,productId,cartBean);
        //
        Long cartCount=redisTemplate.opsForHash().size(cartId);
        return ResponseServer.success(cartCount);
    }

    @Override
    public Map<String, Object> queryCart(String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        List <Cart>values = redisTemplate.opsForHash().values(cartId);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (Cart cart:values){
            if(cart.getIsChecked()){
                bigDecimal = bigDecimal.add(cart.getSubtotal());
            }
        }
        List<Cart> carts=new ArrayList<Cart>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("values", values);
        map.put("total", bigDecimal);
        return map;
    }

    @Override
    public void cartsShow(String productId, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        productId="1";
        Integer a = Integer.parseInt(productId);
        Cart cartBean = (Cart) redisTemplate.opsForHash().get(cartId, a);
        cartBean.setChecked(!cartBean.getIsChecked());
        redisTemplate.opsForHash().put(cartId,a, cartBean);
    }

    @Override
    public ResponseServer addCartNum(int productId, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        Integer a=Integer.valueOf(productId);
        Cart cartBean=(Cart) redisTemplate.opsForHash().get(cartId,a);
        cartBean.setSubtotal(cartBean.getPrice().multiply(new BigDecimal(cartBean.getCount())));
        cartBean.setCount(cartBean.getCount()+1);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal count = new BigDecimal(cartBean.getCount());
        BigDecimal subtotal = bigDecimal.add(cartBean.getPrice()).multiply(count);
        cartBean.setSubtotal(subtotal);
        redisTemplate.opsForHash().put(cartId,a,cartBean);
        return ResponseServer.success(cartBean);
    }

    @Override
    public ResponseServer removeCartNum(int productId, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        Integer a=Integer.valueOf(productId);
        Cart cartBean=(Cart) redisTemplate.opsForHash().get(cartId,a);
        cartBean.setSubtotal(cartBean.getPrice().multiply(new BigDecimal(cartBean.getCount())));
        cartBean.setCount(cartBean.getCount()-1);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal subtotal = bigDecimal.add(cartBean.getSubtotal()).subtract(cartBean.getPrice());
        cartBean.setSubtotal(subtotal);
        redisTemplate.opsForHash().put(cartId,a,cartBean);
        return ResponseServer.success(cartBean);
    }

    @Override
    public ResponseServer deleteProduct(int productId, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        Integer a=Integer.valueOf(productId);
        redisTemplate.opsForHash().delete(cartId, a);
        Long size = redisTemplate.opsForHash().size(cartId);
        return ResponseServer.success(size);
    }

    @Override
    public Cart queryCartId(int productId, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        Cart cartBean= (Cart) redisTemplate.opsForHash().get(cartId,productId);
            if(cartBean.getIsChecked()==true){
                cartBean.setChecked(false);
            }else {
                cartBean.setChecked(true);
            }
            redisTemplate.opsForHash().put(cartId,productId,cartBean);
        return cartBean;
    }

    @Override
    public List<Cart> queryCartIdAll(String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        List<Cart> cart = redisTemplate.opsForHash().values(cartId);
        int count=0;
        for (Cart c : cart) {
            if(c.getIsChecked()){
                c.setChecked(false);
                count++;
            }else{
                c.setChecked(true);
            }
            redisTemplate.opsForHash().put(cartId,c.getProductId(),c);
        }
        return cart;
    }

    @Override
    public void changeContent(int productId, Integer count, String userPhone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        Integer i = Integer.valueOf(productId);
        Cart cart = (Cart) redisTemplate.opsForHash().get(cartId,i);
        cart.setCount(count);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal counts = new BigDecimal(cart.getCount());
        BigDecimal subtotal = bigDecimal.add(cart.getPrice()).multiply(counts);
        cart.setSubtotal(subtotal);
        redisTemplate.opsForHash().put(cartId,productId,cart);
    }
}
