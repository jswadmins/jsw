package com.fh.controller;

import com.fh.model.Brand.Brand;
import com.fh.model.Brand.BrandSearchParam;
import com.fh.model.shop.Shop;
import com.fh.model.shop.ShopSearchParam;
import com.fh.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @PutMapping("/queryList")
    @ResponseBody
    Map<String, Object> queryList(@RequestBody BrandSearchParam brandSearchParam){
        Long count=brandService.queryCount(brandSearchParam);
        List<Brand> list = brandService.querylist(brandSearchParam);
        Map resultMap=new HashMap();
        resultMap.put("draw",brandSearchParam.getDraw());
        resultMap.put("recordsFiltered",count);
        resultMap.put("recordsTotal",count);
        resultMap.put("data",list);
        return resultMap;
    }
    @PutMapping("/brandQuery")
    @ResponseBody
    Map brandQuery(@RequestBody int pid){
        Map resultMap=new HashMap();
       List<Brand> list = brandService.brandQuery(pid);
        resultMap.put("data",list);
        return resultMap;
    }

}
