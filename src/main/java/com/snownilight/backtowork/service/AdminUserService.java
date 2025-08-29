package com.snownilight.backtowork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snownilight.backtowork.mapper.AdminRoleMapper;
import com.snownilight.backtowork.mapper.AdminUserMapper;
import com.snownilight.backtowork.model.dto.CreateOrUpdateAdminUser;
import com.snownilight.backtowork.model.enums.AdminRoleEnum;
import com.snownilight.backtowork.model.po.AdminRole;
import com.snownilight.backtowork.model.po.AdminUser;
import com.snownilight.backtowork.model.vo.AdminUserInfoVO;

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

    @Transactional
    public Optional<AdminUserInfoVO> createAdminUser(CreateOrUpdateAdminUser adminUser, Boolean createBySuperAdmin) {
        // Check if username already exists
        if (adminUserMapper.existByUsername(adminUser.getUsername()) > 0) {
            throw new IllegalArgumentException("Username already exists");
        }

        AdminUser createAdminUser = AdminUser.builder()
                .username(adminUser.getUsername())
                .passwordHash(passwordEncoder.encode(adminUser.getPassword()))
                .roleId(createBySuperAdmin ? AdminRoleEnum.MANAGER.getId() : AdminRoleEnum.PENDING.getId())
                .status(1) // Default to enabled
                .build();

        try { // Handle race condition for duplicate usernames
            adminUserMapper.createAdminUser(createAdminUser);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        AdminUserInfoVO adminUserInfo = AdminUserInfoVO.builder()
                .id(createAdminUser.getId())
                .username(createAdminUser.getUsername())
                .role(AdminRoleEnum.fromId(createAdminUser.getRoleId()).getName())
                .status(createAdminUser.getStatus())
                .build();
        return Optional.of(adminUserInfo);
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