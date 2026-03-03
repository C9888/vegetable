package com.mall.vegetable.dao;

import com.mall.vegetable.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username, password, role, email, phone, status, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{role}, #{email}, #{phone}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE user SET username = #{username}, email = #{email}, phone = #{phone}, " +
            "avatar = #{avatar}, status = #{status}, update_time = NOW() WHERE id = #{id}")
    int update(User user);

    @Update("UPDATE user SET password = #{password}, update_time = NOW() WHERE id = #{id}")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);
    
    @Update("UPDATE user SET phone = #{phone}, update_time = NOW() WHERE id = #{id}")
    int updatePhone(@Param("id") Integer id, @Param("phone") String phone);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM user WHERE role = #{role}")
    List<User> findByRole(Integer role);

    @Select("SELECT * FROM user")
    List<User> findAll();
}
