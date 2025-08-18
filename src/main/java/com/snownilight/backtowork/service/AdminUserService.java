package com.snownilight.backtowork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.snownilight.backtowork.mapper.AdminRoleMapper;
import com.snownilight.backtowork.mapper.AdminUserMapper;
import com.snownilight.backtowork.model.po.AdminRole;
import com.snownilight.backtowork.model.po.AdminUser;

@Service
public class AdminUserService {
    private final AdminUserMapper adminUserMapper;
    private final AdminRoleMapper adminRoleMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserService(AdminUserMapper adminUserMapper, AdminRoleMapper adminRoleMapper) {
        this.adminUserMapper = adminUserMapper;
        this.adminRoleMapper = adminRoleMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<AdminUser> findByUsername(String username) {
        return Optional.ofNullable(adminUserMapper.findByUsername(username));
    }

    public Optional<AdminUser> createAdminUser(AdminUser adminUser) {
        // Encrypt the password before saving
        adminUser.setPasswordHash(passwordEncoder.encode(adminUser.getPasswordHash()));
        boolean created = adminUserMapper.createAdminUser(adminUser);
        return created ? Optional.of(adminUser) : Optional.empty();
    }

    public Optional<AdminUser> updateAdminUser(AdminUser adminUser) {
        // Encrypt the password before updating
        adminUser.setPasswordHash(passwordEncoder.encode(adminUser.getPasswordHash()));
        boolean updated = adminUserMapper.updateAdminUser(adminUser);
        return updated ? Optional.of(adminUser) : Optional.empty();
    }

    public Optional<List<AdminRole>> listAllRoles() {
        return Optional.ofNullable(adminRoleMapper.findAll());
    }

    public boolean validatePassword(AdminUser user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }
}