package com.fh.shopredis.controller;


import com.fh.shopredis.model.PageBean;
import com.fh.shopredis.model.Product;
import com.fh.shopredis.service.IProductService;
import com.fh.shopredis.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;
    @Autowired
    RedisService redisService;
    /*@PostMapping("queryPage")
    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)*/
    @RequestMapping("/bbb")
    @ResponseBody
    public PageBean<Product> queryProduct(@RequestBody PageBean<Product> page) {
        List<Product> list=(List<Product>)redisService.getObj("productPage");
        page=productService.queryProduct(page);
        if (list==null){
            List<Product>listAll=productService.productAll();
            redisService.setObj("productPage",listAll);
            System.out.println("走数据库");
            for (Product  u: listAll) {
                //这里循环user 把每个对象存到 redis中list中
                redisService.addList("list",u);
            }
            int sum=page.getStart()+page.getLength();
            List<Product>pageList=redisService.getListPage("list",page.getStart(),sum-1);
            page.setData(pageList);
            return page;
        }else {
            int sum=page.getStart()+page.getLength();
            List<Product>pageList=redisService.getListPage("list",page.getStart(),sum-1);
            page.setData(pageList);
            System.out.println("走缓存");
            return page;
        }



       /*   List<Product> list=(List<Product>) redisService.getObj("productPage");

      if (list==null){
            page=productService.queryProduct(page);
            redisService.setObj("productPage",page.getData());
            System.out.println("走数据库");
            return  page;
        }else {
            System.out.println("走缓存");
            page.setData(list);
            return page;
        }*/
    }
}
