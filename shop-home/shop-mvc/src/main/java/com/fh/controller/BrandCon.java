package com.fh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.commons.HttpRequestUtil;
import com.fh.model.BrandSearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/brandtest")
public class BrandCon {

    @RequestMapping("/queryBrand")
    @ResponseBody
    String queryBrand(BrandSearchParam brandSearchParam){
        ObjectMapper obj =new ObjectMapper();
        String objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(brandSearchParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8090/brand/queryList";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        System.out.println(str);
        return str;
    }
    @RequestMapping("/brandQuery")
    @ResponseBody
    Map brandQuery(int pid){
        Map map=new HashMap();
        ObjectMapper obj =new ObjectMapper();
        String objectWriter = null;
        try {
            objectWriter = obj.writeValueAsString(pid);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = "http://localhost:8090/brand/brandQuery";
        String str = HttpRequestUtil.doPut(url,null,objectWriter);
        map.put("code","200");
        map.put("a",str);
        System.out.println(str);
        return map;
    }

}
