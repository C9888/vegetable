package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.RecipeRating;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecipeRatingMapper {
    
    @Insert("INSERT INTO recipe_rating(recipe_id, user_id, score, comment) VALUES(#{recipeId}, #{userId}, #{score}, #{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(RecipeRating rating);
    
    @Update("UPDATE recipe_rating SET score = #{score}, comment = #{comment} WHERE recipe_id = #{recipeId} AND user_id = #{userId}")
    int update(RecipeRating rating);
    
    @Select("SELECT * FROM recipe_rating WHERE recipe_id = #{recipeId} AND user_id = #{userId}")
    RecipeRating findByRecipeAndUser(@Param("recipeId") Integer recipeId, @Param("userId") Integer userId);
    
    @Select("SELECT * FROM recipe_rating WHERE recipe_id = #{recipeId} ORDER BY create_time DESC")
    List<RecipeRating> findByRecipeId(@Param("recipeId") Integer recipeId);
    
    @Select("SELECT AVG(score) as avgScore, COUNT(*) as count FROM recipe_rating WHERE recipe_id = #{recipeId}")
    Map<String, Object> getAverageScore(@Param("recipeId") Integer recipeId);
    
    @Select("SELECT r.*, u.username FROM recipe_rating r " +
            "LEFT JOIN user u ON r.user_id = u.id " +
            "WHERE r.recipe_id = #{recipeId} ORDER BY r.create_time DESC")
    List<RecipeRating> findByRecipeIdWithUsername(@Param("recipeId") Integer recipeId);
}
