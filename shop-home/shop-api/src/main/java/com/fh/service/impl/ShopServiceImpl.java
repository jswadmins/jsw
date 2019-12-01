package com.fh.service.impl;

import com.fh.controller.ResponseServer;
import com.fh.dao.ShopDao;
import com.fh.model.shop.PoShop;
import com.fh.model.shop.Shop;
import com.fh.model.shop.ShopSearchParam;
import com.fh.service.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopDao shopDao;

    @Override
    public List<Map<String, Object>> queryList() {
        //shopDao.queryList();
        List<Shop> shop = shopDao.queryList();
        return getCategory(0,shop);

    }
    private List<Map<String, Object>> getCategory(Integer pid, List<Shop> shop) {
        List<Map<String, Object>> list = new ArrayList<>();
        shop.forEach(cate -> {
            Map<String, Object> map = null;
            if (pid.equals(cate.getPid())) {
                map = new HashMap<>();
                map.put("shopId", cate.getShopId());
                map.put("shopName", cate.getShopName());
                map.put("pid", cate.getPid());
                map.put("children", getCategory(cate.getShopId(), shop));
            }
            if (map != null) {
                list.add(map);
            }
        });
        return list;
    }

    @Override
    public Long queryCount(ShopSearchParam shopSearchParam) {
        Long loang = shopDao.queryCount(shopSearchParam);
        return loang;
    }

    @Override
    public List<Shop> querylist(ShopSearchParam shopSearchParam) {
        List<Shop> querylistVo = new ArrayList<>();
        List<PoShop> querylist = shopDao.querylist(shopSearchParam);
        for (PoShop userinfo : querylist){
            Shop vo = getUservo(userinfo);
            querylistVo.add(vo);
        }
        return querylistVo;
    }

    @Override
    public ResponseServer getProductById(Integer productId) {
        Shop product=shopDao.getProductById(productId);
        return ResponseServer.success(product);
    }

    private Shop getUservo(PoShop userinfo) {
        Shop vo=new Shop();
        vo.setShopId(userinfo.getShopId());
        vo.setBrandId(userinfo.getBrandId());
        vo.setShopName(userinfo.getShopName());
        vo.setSubtitle(userinfo.getSubtitle());
        vo.setDetail(userinfo.getDetail());
        vo.setPrice(userinfo.getPrice());
        vo.setStock(userinfo.getStock());
        vo.setStatus(userinfo.getStatus());
        vo.setPid(userinfo.getPid());
        vo.setImgPath(userinfo.getImgPath());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(userinfo.getCreatetime());
        vo.setCreatetime(format);
        return vo;
    }

}
