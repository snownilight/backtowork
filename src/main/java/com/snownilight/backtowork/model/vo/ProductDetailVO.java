package com.snownilight.backtowork.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailVO {
    // 產品基本資訊
    private Long id;                     // 產品ID
    private String name;                 // 產品名稱
    private String description;          // 產品描述

    // 商品項目資訊
    private BigDecimal price;            // 價格
    private Integer stock;               // 庫存
    private Integer status;               // 狀態(0下架, 1上架)
    private LocalDateTime itemCreatedAt; // 商品項目建立時間
    private LocalDateTime itemUpdatedAt; // 商品項目更新時間
}
