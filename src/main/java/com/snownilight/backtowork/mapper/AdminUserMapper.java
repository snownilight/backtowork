package com.snownilight.backtowork.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.snownilight.backtowork.model.po.AdminUser;

@Mapper
public interface AdminUserMapper {
    AdminUser findByName(String username);
    boolean insertAdminUser(AdminUser adminUser);
    boolean updateAdminUser(AdminUser adminUser);
    boolean deleteAdminUser(Long id);
    boolean updateAdminUserStatus(Long id, Integer status);
    boolean updateAdminUserPassword(Long id, String passwordHash);
    boolean updateAdminUserLastLogin(Long id, String lastLoginAt);
}
