package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.Vegetable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VegetableMapper {

    @Select("SELECT id, name, name_en as nameEn, category, image, nutrition, benefits, selection_tips as selectionTips, storage_method as storageMethod, description, status, calories, create_time as createTime, update_time as updateTime FROM vegetable WHERE id = #{id}")
    Vegetable findById(Integer id);

    @Select("SELECT * FROM vegetable WHERE name = #{name}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "nameEn", column = "name_en"),
        @Result(property = "category", column = "category"),
        @Result(property = "image", column = "image"),
        @Result(property = "nutrition", column = "nutrition"),
        @Result(property = "benefits", column = "benefits"),
        @Result(property = "selectionTips", column = "selection_tips"),
        @Result(property = "storageMethod", column = "storage_method"),
        @Result(property = "description", column = "description"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    Vegetable findByName(String name);

    @Select("SELECT * FROM vegetable WHERE status = 1 ORDER BY id DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "nameEn", column = "name_en"),
        @Result(property = "category", column = "category"),
        @Result(property = "image", column = "image"),
        @Result(property = "nutrition", column = "nutrition"),
        @Result(property = "benefits", column = "benefits"),
        @Result(property = "selectionTips", column = "selection_tips"),
        @Result(property = "storageMethod", column = "storage_method"),
        @Result(property = "description", column = "description"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Vegetable> findAll();

    @Select("SELECT * FROM vegetable WHERE status = 1 AND name LIKE CONCAT('%', #{keyword}, '%')")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "nameEn", column = "name_en"),
        @Result(property = "category", column = "category"),
        @Result(property = "image", column = "image"),
        @Result(property = "nutrition", column = "nutrition"),
        @Result(property = "benefits", column = "benefits"),
        @Result(property = "selectionTips", column = "selection_tips"),
        @Result(property = "storageMethod", column = "storage_method"),
        @Result(property = "description", column = "description"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Vegetable> searchByKeyword(String keyword);

    @Select("SELECT * FROM vegetable WHERE status = 1 ORDER BY id DESC LIMIT #{limit}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "nameEn", column = "name_en"),
        @Result(property = "category", column = "category"),
        @Result(property = "image", column = "image"),
        @Result(property = "nutrition", column = "nutrition"),
        @Result(property = "benefits", column = "benefits"),
        @Result(property = "selectionTips", column = "selection_tips"),
        @Result(property = "storageMethod", column = "storage_method"),
        @Result(property = "description", column = "description"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Vegetable> findPopular(int limit);

    @Select("SELECT * FROM vegetable WHERE status = 1 AND category = #{category}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "nameEn", column = "name_en"),
        @Result(property = "category", column = "category"),
        @Result(property = "image", column = "image"),
        @Result(property = "nutrition", column = "nutrition"),
        @Result(property = "benefits", column = "benefits"),
        @Result(property = "selectionTips", column = "selection_tips"),
        @Result(property = "storageMethod", column = "storage_method"),
        @Result(property = "description", column = "description"),
        @Result(property = "status", column = "status"),
        @Result(property = "calories", column = "calories"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Vegetable> findByCategory(String category);

    @Insert("INSERT INTO vegetable (name, name_en, category, image, nutrition, benefits, selection_tips, storage_method, description, status, calories, create_time, update_time) " +
            "VALUES (#{name}, #{nameEn}, #{category}, #{image}, #{nutrition}, #{benefits}, #{selectionTips}, #{storageMethod}, #{description}, #{status}, #{calories}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Vegetable vegetable);

    @Update("UPDATE vegetable SET name = #{name}, name_en = #{nameEn}, category = #{category}, image = #{image}, " +
            "nutrition = #{nutrition}, benefits = #{benefits}, selection_tips = #{selectionTips}, " +
            "storage_method = #{storageMethod}, description = #{description}, status = #{status}, calories = #{calories}, " +
            "update_time = NOW() WHERE id = #{id}")
    int update(Vegetable vegetable);

    @Delete("DELETE FROM vegetable WHERE id = #{id}")
    int deleteById(Integer id);
}
