package com.snownilight.backtowork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.snownilight.backtowork.model.po.AdminRole;

@Mapper
public interface AdminRoleMapper {
    List<AdminRole> findAll();
}
