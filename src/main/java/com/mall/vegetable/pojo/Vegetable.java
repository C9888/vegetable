package com.mall.vegetable.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Vegetable {
    private Integer id;
    private String name;
    private String nameEn;
    private String category;
    private String image;
    private String nutrition;
    private String benefits;
    private String selectionTips;
    private String storageMethod;
    private String description;
    private Integer status;
    private Integer calories;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
