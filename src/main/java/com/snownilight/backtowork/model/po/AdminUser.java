package com.snownilight.backtowork.model.po;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
    private Long id;               // 主鍵 ID
    private String username;       // 使用者名稱
    private String passwordHash;   // 密碼（BCrypt 加密）
    private String nickname;       // 暱稱
    private String email;          // 信箱
    private String role;           // 角色 (e.g. SUPER_ADMIN, MANAGER)
    private Integer status;        // 狀態 (0: 停用, 1: 啟用)
    private LocalDateTime lastLoginAt; // 最後登入時間
    private LocalDateTime createdAt;   // 建立時間
    private LocalDateTime updatedAt;   // 更新時間
}
