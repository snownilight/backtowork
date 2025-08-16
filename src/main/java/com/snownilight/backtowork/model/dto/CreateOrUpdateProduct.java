package com.snownilight.backtowork.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateProduct {
    private Long id;             // 商品ID
    @NotBlank(message = "商品名稱不能為空")
    @Size(max = 100, message = "商品名稱不能超過100字")
    private String name;          // 商品名稱
    @Size(max = 500, message = "商品描述不能超過500字")
    private String description;   // 商品描述
    @NotNull(message = "商品價格不能為空")
    @DecimalMin(value = "0.0", inclusive = false, message = "商品價格必須大於0")
    private BigDecimal price;     // 價格
    @NotNull(message = "庫存不能為空")
    @Min(value = 0, message = "庫存不能小於0")
    private Integer stock;        // 庫存數量
    @NotNull(message = "狀態必填")
    @Min(value = 0, message = "狀態必須是0或1")
    @Max(value = 1, message = "狀態必須是0或1")
    private Integer status;       // 狀態 (0下架, 1上架)
}