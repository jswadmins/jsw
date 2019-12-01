package com.fh.service;

import com.fh.model.Brand.Brand;
import com.fh.model.Brand.BrandSearchParam;
import com.fh.model.shop.Shop;

import java.util.List;

public interface BrandService {
    List<Brand> querylist(BrandSearchParam brandSearchParam);

    Long queryCount(BrandSearchParam brandSearchParam);

    List<Brand> brandQuery(int pid);
}
