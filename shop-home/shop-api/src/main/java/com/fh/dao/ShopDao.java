package com.fh.dao;

import com.fh.model.shop.PoShop;
import com.fh.model.shop.Shop;
import com.fh.model.shop.ShopSearchParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopDao {
    List<Shop> queryList();

    Long queryCount(ShopSearchParam shopSearchParam);

    List<PoShop> querylist(ShopSearchParam shopSearchParam);

    Shop getProductById(Integer productId);
}
