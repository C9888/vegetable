package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.RecognitionHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecognitionHistoryMapper {

    @Select("SELECT * FROM recognition_history WHERE id = #{id}")
    RecognitionHistory findById(Integer id);

    @Select("SELECT * FROM recognition_history WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<RecognitionHistory> findByUserId(Integer userId);

    @Select("SELECT * FROM recognition_history WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT #{limit}")
    List<RecognitionHistory> findRecentByUserId(@Param("userId") Integer userId, @Param("limit") int limit);

    @Insert("INSERT INTO recognition_history (user_id, vegetable_name, image_url, confidence, create_time) " +
            "VALUES (#{userId}, #{vegetableName}, #{imageUrl}, #{confidence}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(RecognitionHistory history);

    @Delete("DELETE FROM recognition_history WHERE id = #{id}")
    int deleteById(Integer id);
}
