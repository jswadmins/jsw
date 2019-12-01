package com.fh.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("s_customer")
@Data
public class CustomerBean implements Serializable {

    @TableId(value = "customerId" , type = IdType.AUTO)
    private Integer customerId;
    @TableField("loginName")
    private String loginName;
    @TableField("phone")
    private String  phone;
    @TableField("userStats")
    private Integer userStats;
    @TableField("modifiedTime")
    private Date  modifiedTime;
    @TableField("password")
    private String password;
    @TableField("cartId")
    private String cartId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserStats() {
        return userStats;
    }

    public void setUserStats(Integer userStats) {
        this.userStats = userStats;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
