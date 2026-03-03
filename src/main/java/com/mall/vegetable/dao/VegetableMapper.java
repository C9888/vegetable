package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.Vegetable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VegetableMapper {

    @Select("SELECT * FROM vegetable WHERE id = #{id}")
    Vegetable findById(Integer id);

    @Select("SELECT * FROM vegetable WHERE name = #{name}")
    Vegetable findByName(String name);

    @Select("SELECT * FROM vegetable WHERE status = 1 ORDER BY id DESC")
    List<Vegetable> findAll();

    @Select("SELECT * FROM vegetable WHERE status = 1 AND name LIKE CONCAT('%', #{keyword}, '%')")
    List<Vegetable> searchByKeyword(String keyword);

    @Select("SELECT * FROM vegetable WHERE status = 1 ORDER BY id DESC LIMIT #{limit}")
    List<Vegetable> findPopular(int limit);

    @Select("SELECT * FROM vegetable WHERE status = 1 AND category = #{category}")
    List<Vegetable> findByCategory(String category);

    @Insert("INSERT INTO vegetable (name, name_en, category, image, nutrition, benefits, selection_tips, storage_method, description, status, create_time, update_time) " +
            "VALUES (#{name}, #{nameEn}, #{category}, #{image}, #{nutrition}, #{benefits}, #{selectionTips}, #{storageMethod}, #{description}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Vegetable vegetable);

    @Update("UPDATE vegetable SET name = #{name}, name_en = #{nameEn}, category = #{category}, image = #{image}, " +
            "nutrition = #{nutrition}, benefits = #{benefits}, selection_tips = #{selectionTips}, " +
            "storage_method = #{storageMethod}, description = #{description}, status = #{status}, update_time = NOW() WHERE id = #{id}")
    int update(Vegetable vegetable);

    @Delete("DELETE FROM vegetable WHERE id = #{id}")
    int deleteById(Integer id);
}
