package com.snownilight.backtowork.model.po;

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
    private Long roleId;           // 權限 (對應 AdminRole 的 ID)
    private Integer status;        // 狀態 (0: 停用, 1: 啟用)
}
