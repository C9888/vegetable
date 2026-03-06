package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.Recipe;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecipeMapper {

    @Select("SELECT * FROM recipe WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "vegetableId", column = "vegetable_id"),
        @Result(property = "image", column = "image"),
        @Result(property = "difficulty", column = "difficulty"),
        @Result(property = "time", column = "time"),
        @Result(property = "servings", column = "servings"),
        @Result(property = "ingredients", column = "ingredients"),
        @Result(property = "steps", column = "steps"),
        @Result(property = "tips", column = "tips"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    Recipe findById(Integer id);

    @Select("SELECT * FROM recipe WHERE vegetable_id = #{vegetable_id} AND status = 1")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "vegetableId", column = "vegetable_id"),
        @Result(property = "image", column = "image"),
        @Result(property = "difficulty", column = "difficulty"),
        @Result(property = "time", column = "time"),
        @Result(property = "servings", column = "servings"),
        @Result(property = "ingredients", column = "ingredients"),
        @Result(property = "steps", column = "steps"),
        @Result(property = "tips", column = "tips"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Recipe> findByVegetableId(Integer vegetableId);

    @Select("SELECT * FROM recipe WHERE status = 1 ORDER BY id DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "vegetableId", column = "vegetable_id"),
        @Result(property = "image", column = "image"),
        @Result(property = "difficulty", column = "difficulty"),
        @Result(property = "time", column = "time"),
        @Result(property = "servings", column = "servings"),
        @Result(property = "ingredients", column = "ingredients"),
        @Result(property = "steps", column = "steps"),
        @Result(property = "tips", column = "tips"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Recipe> findAll();

    @Insert("INSERT INTO recipe (name, vegetable_id, image, difficulty, time, servings, ingredients, steps, tips, status, calories, create_time, update_time) " +
            "VALUES (#{name}, #{vegetableId}, #{image}, #{difficulty}, #{time}, #{servings}, #{ingredients}, #{steps}, #{tips}, #{status}, #{calories}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Recipe recipe);

    @Update("UPDATE recipe SET name = #{name}, vegetable_id = #{vegetableId}, image = #{image}, difficulty = #{difficulty}, " +
            "time = #{time}, servings = #{servings}, ingredients = #{ingredients}, steps = #{steps}, tips = #{tips}, " +
            "status = #{status}, calories = #{calories}, update_time = NOW() WHERE id = #{id}")
    int update(Recipe recipe);

    @Delete("DELETE FROM recipe WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM recipe WHERE name LIKE CONCAT('%', #{keyword}, '%') AND status = 1 ORDER BY id DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "vegetableId", column = "vegetable_id"),
        @Result(property = "image", column = "image"),
        @Result(property = "difficulty", column = "difficulty"),
        @Result(property = "time", column = "time"),
        @Result(property = "servings", column = "servings"),
        @Result(property = "ingredients", column = "ingredients"),
        @Result(property = "steps", column = "steps"),
        @Result(property = "tips", column = "tips"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Recipe> searchByKeyword(String keyword);
}
