package com.mall.vegetable.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecipeRating {
    private Integer id;
    private Integer recipeId;
    private Integer userId;
    private Integer score;
    private String comment;
    private LocalDateTime createTime;
}
