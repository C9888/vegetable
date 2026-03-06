package com.mall.vegetable.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Recipe {
    private Integer id;
    private String name;
    private Integer vegetableId;
    private String image;
    private String difficulty;
    private String time;
    private Integer servings;
    private String ingredients;
    private String steps;
    private String tips;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String vegetableName;
    private Double avgScore;
    private Integer ratingCount;
}
