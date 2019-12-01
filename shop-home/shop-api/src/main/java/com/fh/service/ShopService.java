package com.fh.service;

import com.fh.controller.ResponseServer;
import com.fh.model.shop.Shop;
import com.fh.model.shop.ShopSearchParam;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<Map<String, Object>> queryList();

    Long queryCount(ShopSearchParam shopSearchParam);

    List<Shop> querylist(ShopSearchParam shopSearchParam);

    ResponseServer getProductById(Integer productId);
}
