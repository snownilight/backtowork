package com.snownilight.backtowork.model.po;

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
public class ProductItem {
    private Long id;                     // 商品項目ID
    private Long productId;              // 對應產品ID
    private BigDecimal price;            // 價格
    private Integer stock;               // 庫存數量
    private Integer status;              // 狀態(0下架, 1上架)
    private LocalDateTime createdAt;     // 建立時間
    private LocalDateTime updatedAt;     // 更新時間
}
