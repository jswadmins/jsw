package com.fh.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Shop {

    ///主键
    private int shopId;
    //类型ID
    private String brandId;
    //商品名称
    private String shopName;
    //宣传标题
    private String subtitle;
    //商品描述
    private String detail;
    //价格
    private int price;
    //库存
    private int stock;
    //状态
    private int status;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
