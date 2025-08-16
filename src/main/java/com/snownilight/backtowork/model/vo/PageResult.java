package com.snownilight.backtowork.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResult<T> {
    private Integer page;      // 當前頁碼
    private Integer size;      // 每頁顯示的數量
    private Long totalElements; // 總元素數量
    private Integer totalPages; // 總頁數
    private T content;          // 當前頁的內容

    public PageResult(Integer page, Integer size, Long totalElements, Integer totalPages, T content) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.content = content;
    }
}
