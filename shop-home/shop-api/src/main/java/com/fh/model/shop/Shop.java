package com.fh.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fh.commons.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Shop extends Page {

    //主键
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
    private String createtime;
    //pid
    private int pid;
    //图片
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
