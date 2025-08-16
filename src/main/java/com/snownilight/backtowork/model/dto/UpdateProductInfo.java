package com.snownilight.backtowork.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductInfo {
    @NotNull(message = "商品ID不能為空")
    private Long id;             // 商品ID
    @NotBlank(message = "商品名稱不能為空")
    @Size(max = 100, message = "商品名稱不能超過100字")
    private String name;          // 商品名稱
    @Size(max = 500, message = "商品描述不能超過500字")
    private String description;   // 商品描述
}
