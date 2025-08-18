package com.snownilight.backtowork.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.snownilight.backtowork.model.po.AdminUser;

@Mapper
public interface AdminUserMapper {
    AdminUser findByUsername(String username);
    boolean createAdminUser(AdminUser adminUser);
    boolean updateAdminUser(AdminUser adminUser);
}
