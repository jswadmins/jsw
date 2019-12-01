package com.fh.shopredis.service;


import com.fh.shopredis.model.PageBean;
import com.fh.shopredis.model.Product;

import java.util.List;

public interface IProductService {
    PageBean<Product> queryProduct(PageBean<Product> page);

    List<Product> productAll();
}
