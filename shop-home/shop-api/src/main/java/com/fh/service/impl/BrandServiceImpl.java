package com.fh.service.impl;

import com.fh.dao.BrandDao;
import com.fh.model.Brand.Brand;
import com.fh.model.Brand.BrandSearchParam;
import com.fh.model.Brand.PoBrand;
import com.fh.model.shop.PoShop;
import com.fh.model.shop.Shop;
import com.fh.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public List<Brand> querylist(BrandSearchParam brandSearchParam) {
        List<Brand> querylistVo = new ArrayList<>();
        List<PoBrand> querylist = brandDao.querylist(brandSearchParam);
        for (PoBrand userinfo : querylist){
            Brand vo = getUservo(userinfo);
            querylistVo.add(vo);
        }
        return querylistVo;
    }

    @Override
    public Long queryCount(BrandSearchParam brandSearchParam) {
       Long count = brandDao.queryCount(brandSearchParam);
        return count;
    }

    @Override
    public List<Brand> brandQuery(int pid) {
        return brandDao.brandQuery(pid);
    }

    private Brand getUservo(PoBrand userinfo) {
        Brand vo=new Brand();
        vo.setId(userinfo.getId());
        vo.setBrandName(userinfo.getBrandName());
        vo.setTypeId(userinfo.getTypeId());
        vo.setPhone(userinfo.getPhone());
        vo.setWebsite(userinfo.getWebsite());
        return vo;
    }

}
