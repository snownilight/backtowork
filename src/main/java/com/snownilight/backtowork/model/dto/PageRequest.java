package com.snownilight.backtowork.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private Integer page;      // 當前頁碼，從1開始
    private Integer size;      // 每頁顯示的數量
    private String search;   // 搜索關鍵字
    private String sortBy;     // 排序字段
    private String sortOrder;  // 排序方式（asc或desc）

    public Integer getOffset() {
        return (page - 1) * size; // 計算偏移量
    }
    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        this.sortBy = "id"; // 默認按ID排序
        this.sortOrder = "asc"; // 默認升序
    }
}
