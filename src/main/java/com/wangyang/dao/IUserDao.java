package com.wangyang.dao;

import com.wangyang.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface IUserDao {
    @Insert("insert into user (id,username,password) values (9968,'zzzzzz','111111')")
    void add(User user);
    @Select("SELECT * FROM user WHERE id = #{userId}")
    User findUserById(@Param("userId") int id);
}