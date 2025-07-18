package com.study.mapper;

import com.study.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findByUsername(@Param("username") String username);
    User findById(@Param("id") Long id);
    boolean existsByUsername(@Param("username") String username);
    boolean existsByEmail(@Param("email") String email);
}
