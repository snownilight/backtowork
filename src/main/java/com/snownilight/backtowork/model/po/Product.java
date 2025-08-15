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
public class Product {
    private Long id;                     // 產品ID
    private String name;                 // 產品名稱
    private String description;          // 產品描述
    private LocalDateTime createdAt;      // 建立時間
    private LocalDateTime updatedAt;      // 更新時間
}
