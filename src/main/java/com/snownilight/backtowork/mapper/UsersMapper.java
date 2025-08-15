package com.snownilight.backtowork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.snownilight.backtowork.model.po.Users;

@Mapper
public interface UsersMapper {
    Users findById(Long id);
    
    List<Users> findAll();
    
    Long insert(Users user);
    
    boolean update(Users user);
    
    boolean delete(Long id);
}
