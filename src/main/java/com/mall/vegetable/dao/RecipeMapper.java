package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.Recipe;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecipeMapper {

    @Select("SELECT * FROM recipe WHERE id = #{id}")
    Recipe findById(Integer id);

    @Select("SELECT * FROM recipe WHERE vegetable_id = #{vegetableId} AND status = 1")
    List<Recipe> findByVegetableId(Integer vegetableId);

    @Select("SELECT * FROM recipe WHERE status = 1 ORDER BY id DESC")
    List<Recipe> findAll();

    @Insert("INSERT INTO recipe (name, vegetable_id, image, difficulty, time, servings, ingredients, steps, tips, status, create_time, update_time) " +
            "VALUES (#{name}, #{vegetableId}, #{image}, #{difficulty}, #{time}, #{servings}, #{ingredients}, #{steps}, #{tips}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Recipe recipe);

    @Update("UPDATE recipe SET name = #{name}, vegetable_id = #{vegetableId}, image = #{image}, difficulty = #{difficulty}, " +
            "time = #{time}, servings = #{servings}, ingredients = #{ingredients}, steps = #{steps}, tips = #{tips}, " +
            "status = #{status}, update_time = NOW() WHERE id = #{id}")
    int update(Recipe recipe);

    @Delete("DELETE FROM recipe WHERE id = #{id}")
    int deleteById(Integer id);
}
