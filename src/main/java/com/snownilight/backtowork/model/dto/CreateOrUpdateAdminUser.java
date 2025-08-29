package com.snownilight.backtowork.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAdminUser {
    private Long id;               // 主鍵 ID
    @NotBlank(message = "帳號不能為空")
    @Size(min = 4, max = 20, message = "帳號長度需介於4到20字元")
    private String username;       // 使用者名稱
    @NotBlank(message = "密碼不能為空")
    @Size(min = 6, message = "密碼至少6字元")
    private String password;       // 密碼（明文，將在服務端加密）
    private Long roleId;           // 權限 (對應 AdminRole 的 ID)
    private Integer status;        // 狀態 (0: 停用, 1: 啟用)
}