package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.BrowseHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BrowseHistoryMapper {
    
    @Insert("INSERT INTO browse_history(user_id, vegetable_id) VALUES(#{userId}, #{vegetableId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(BrowseHistory history);
    
    @Select("SELECT h.*, v.id as ve_id, v.name as ve_name, v.category as ve_category, " +
            "v.description as ve_description, v.image as ve_image " +
            "FROM browse_history h " +
            "LEFT JOIN vegetable v ON h.vegetable_id = v.id " +
            "WHERE h.user_id = #{userId} " +
            "ORDER BY h.browse_time DESC " +
            "LIMIT 3")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "vegetableId", column = "vegetable_id"),
        @Result(property = "browseTime", column = "browse_time"),
        @Result(property = "vegetable.id", column = "ve_id"),
        @Result(property = "vegetable.name", column = "ve_name"),
        @Result(property = "vegetable.category", column = "ve_category"),
        @Result(property = "vegetable.description", column = "ve_description"),
        @Result(property = "vegetable.image", column = "ve_image")
    })
    List<BrowseHistory> findByUserId(@Param("userId") Integer userId);
    
    @Select("SELECT COUNT(*) FROM browse_history WHERE user_id = #{userId}")
    int countByUserId(@Param("userId") Integer userId);
    
    @Delete("DELETE FROM browse_history WHERE user_id = #{userId} AND id NOT IN " +
            "(SELECT id FROM (SELECT id FROM browse_history WHERE user_id = #{userId} " +
            "ORDER BY browse_time DESC LIMIT 3) AS temp)")
    void deleteOldHistory(@Param("userId") Integer userId);
}
