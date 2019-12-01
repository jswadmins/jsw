package com.fh.model;

import com.fh.commons.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ShopSearchParam extends Page implements Serializable {

    private String shopName;

    private String shopId;

    private Integer minprice;

    private Integer maxprice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mincreate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxcreate;

    private int pid;

    private String src;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getMinprice() {
        return minprice;
    }

    public void setMinprice(Integer minprice) {
        this.minprice = minprice;
    }

    public Integer getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Integer maxprice) {
        this.maxprice = maxprice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Date getMincreate() {
        return mincreate;
    }

    public void setMincreate(Date mincreate) {
        this.mincreate = mincreate;
    }

    public Date getMaxcreate() {
        return maxcreate;
    }

    public void setMaxcreate(Date maxcreate) {
        this.maxcreate = maxcreate;
    }
}