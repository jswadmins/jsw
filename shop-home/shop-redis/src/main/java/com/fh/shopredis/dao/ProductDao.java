package com.fh.shopredis.dao;

import com.fh.shopredis.model.PageBean;
import com.fh.shopredis.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDao {
    long countProduct();
    List<Product> queryProduct(@Param("page") PageBean<Product> page);

    List<Product> productAll();
}
