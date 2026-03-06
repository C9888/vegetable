package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.RecipeRating;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface RecipeRatingMapper {

    @Insert("INSERT INTO recipe_rating(recipe_id, user_id, score, comment, create_time) VALUES(#{recipeId}, #{userId}, #{score}, #{comment}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(RecipeRating rating);

    @Update("UPDATE recipe_rating SET score = #{score}, comment = #{comment} WHERE recipe_id = #{recipeId} AND user_id = #{userId}")
    int update(RecipeRating rating);

    @Select("SELECT * FROM recipe_rating WHERE recipe_id = #{recipe_id} AND user_id = #{user_id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "recipeId", column = "recipe_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "score", column = "score"),
        @Result(property = "comment", column = "comment"),
        @Result(property = "createTime", column = "create_time")
    })
    RecipeRating findByRecipeAndUser(@Param("recipe_id") Integer recipeId, @Param("user_id") Integer userId);

    @Select("SELECT * FROM recipe_rating WHERE recipe_id = #{recipe_id} ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "recipeId", column = "recipe_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "score", column = "score"),
        @Result(property = "comment", column = "comment"),
        @Result(property = "createTime", column = "create_time")
    })
    List<RecipeRating> findByRecipeId(@Param("recipe_id") Integer recipeId);

    @Select("SELECT AVG(score) as avgScore, COUNT(*) as count FROM recipe_rating WHERE recipe_id = #{recipe_id}")
    Map<String, Object> getAverageScore(@Param("recipe_id") Integer recipeId);

    @Select("SELECT r.*, u.username FROM recipe_rating r " +
            "LEFT JOIN user u ON r.user_id = u.id " +
            "WHERE r.recipe_id = #{recipe_id} ORDER BY r.create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "recipeId", column = "recipe_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "score", column = "score"),
        @Result(property = "comment", column = "comment"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "username", column = "username")
    })
    List<RecipeRating> findByRecipeIdWithUsername(@Param("recipe_id") Integer recipeId);
}
