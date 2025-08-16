package com.snownilight.backtowork.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductItem {
    @NotNull(message = "商品ID不能為空")
    private Long id;             // 商品ID
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
