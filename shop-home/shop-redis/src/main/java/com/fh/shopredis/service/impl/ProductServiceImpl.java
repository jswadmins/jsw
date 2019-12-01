package com.fh.shopredis.service.impl;

import com.fh.shopredis.dao.ProductDao;
import com.fh.shopredis.model.PageBean;
import com.fh.shopredis.model.Product;
import com.fh.shopredis.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public PageBean<Product> queryProduct(PageBean<Product> page) {
        long count=productDao.countProduct();
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        return page;
    }

    @Override
    public List<Product> productAll() {
        return productDao.productAll();
    }
}
