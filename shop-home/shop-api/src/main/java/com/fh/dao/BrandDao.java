package com.fh.dao;

import com.fh.model.Brand.Brand;
import com.fh.model.Brand.BrandSearchParam;
import com.fh.model.Brand.PoBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandDao {


    Long queryCount(BrandSearchParam brandSearchParam);

    List<PoBrand> querylist(BrandSearchParam brandSearchParam);

    List<Brand> brandQuery(int pid);
}
