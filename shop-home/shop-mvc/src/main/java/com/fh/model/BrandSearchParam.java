package com.fh.model;

import com.fh.commons.Page;

import java.io.Serializable;

public class BrandSearchParam extends Page implements Serializable {

    private String brandName;

    private String phone;


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}