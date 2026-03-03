package com.mall.vegetable.pojo;

import java.util.Date;

public class BrowseHistory {
    private Integer id;
    private Integer userId;
    private Integer vegetableId;
    private Date browseTime;
    
    private Vegetable vegetable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(Integer vegetableId) {
        this.vegetableId = vegetableId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }
}
