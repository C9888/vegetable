package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.RecognitionHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecognitionHistoryMapper {

    @Select("SELECT * FROM recognition_history WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "vegetableName", column = "vegetable_name"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "confidence", column = "confidence"),
        @Result(property = "createTime", column = "create_time")
    })
    RecognitionHistory findById(Integer id);

    @Select("SELECT * FROM recognition_history WHERE user_id = #{user_id} ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "vegetableName", column = "vegetable_name"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "confidence", column = "confidence"),
        @Result(property = "createTime", column = "create_time")
    })
    List<RecognitionHistory> findByUserId(Integer userId);

    @Select("SELECT * FROM recognition_history WHERE user_id = #{user_id} ORDER BY create_time DESC LIMIT #{limit}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "vegetableName", column = "vegetable_name"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "confidence", column = "confidence"),
        @Result(property = "createTime", column = "create_time")
    })
    List<RecognitionHistory> findRecentByUserId(@Param("user_id") Integer userId, @Param("limit") int limit);

    @Insert("INSERT INTO recognition_history (user_id, vegetable_name, image_url, confidence, create_time) " +
            "VALUES (#{userId}, #{vegetableName}, #{imageUrl}, #{confidence}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(RecognitionHistory history);

    @Delete("DELETE FROM recognition_history WHERE id = #{id}")
    int deleteById(Integer id);
}
