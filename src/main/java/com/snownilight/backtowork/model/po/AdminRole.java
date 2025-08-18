package com.snownilight.backtowork.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRole {
    private Long id;               // 主鍵 ID
    private String roleName;       // 角色名稱
    private String description;    // 角色說明
}
