package com.fh.controller;

import com.fh.model.RedisClient;
import com.fh.model.shop.Shop;
import com.fh.model.shop.ShopSearchParam;
import com.fh.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @RequestMapping(value = "queryList",method = RequestMethod.GET)
    @ResponseBody
    Map queryList(){
       Map map=new HashMap();
        List<Map<String, Object>> shop = shopService.queryList();
        map.put("data",shop);
        return map;
    }
    @PutMapping("/queryLists")
    @ResponseBody
    Map<String, Object> queryList(@RequestBody ShopSearchParam shopSearchParam){
        Long count=shopService.queryCount(shopSearchParam);
        List<Shop> list = shopService.querylist(shopSearchParam);
        Map resultMap=new HashMap();
        resultMap.put("draw",shopSearchParam.getDraw());
        resultMap.put("recordsFiltered",count);
        resultMap.put("recordsTotal",count);
        resultMap.put("data",list);
        return resultMap;
    }
    @RequestMapping("/aaa")
    @ResponseBody
    Map aaa(){
        Map resultMap=new HashMap();
            for (int i = 1; i <= 100; i += 1) {
                // 初始化CommentId索引 SortSet
                RedisClient.zadd("topicId", i, "commentId" + i);
                // 初始化Comment数据 Hash
                RedisClient.hset("Comment_Key", "commentId" + i, "comment content "+ i +" .......");
            }
            // 倒序取 从0条开始取 5条 Id 数据
            Set<String> sets = RedisClient.zrevrangebyscore("topicId", "100", "1", 0, 10);
            String[] items = new String[]{};
            System.out.println(sets.toString());
            // 根据id取comment数据
            List<String> list = RedisClient.hmget("Comment_Key", sets.toArray(items));
            resultMap.put("data",list);
            for (String str : list) {
                System.out.println(str);
                resultMap.put("str",str);
            }
        return resultMap;
    }
    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseServer getProductById(@PathVariable Integer productId) {
        return shopService.getProductById(productId);
    }

}
