package com.snownilight.backtowork.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVO {
    private Long id;               // 主鍵 ID
    private String username;       // 使用者名稱
    private String role;           // 權限 (對應 AdminRole 的 name)
    private Integer status;        // 狀態 (0: 停用, 1: 啟用)
}
