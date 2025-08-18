package com.snownilight.backtowork.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snownilight.backtowork.common.ApiResponse;
import com.snownilight.backtowork.model.dto.LoginRequest;
import com.snownilight.backtowork.model.enums.AdminRoleEnum;
import com.snownilight.backtowork.model.vo.LoginResponse;
import com.snownilight.backtowork.service.AdminUserService;
import com.snownilight.backtowork.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {
    private final AdminUserService adminUserService;
    private final JwtUtil jwtUtil;

    public AdminAuthController(AdminUserService adminUserService, JwtUtil jwtUtil) {
        this.adminUserService = adminUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return adminUserService.findByUsername(loginRequest.getUsername())
                .filter(user -> adminUserService.validatePassword(user, loginRequest.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername(), AdminRoleEnum.fromId(user.getRoleId()).getName());
                    return ApiResponse.success(new LoginResponse(token));
                })
                .orElse(ApiResponse.error(401, "Invalid username or password"));
    }
    
}
