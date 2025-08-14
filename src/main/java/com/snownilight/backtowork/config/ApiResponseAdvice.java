package com.snownilight.backtowork.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.snownilight.backtowork.common.ApiResponse;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, 
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, 
                                  @NonNull MethodParameter returnType, 
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, 
                                  @NonNull ServerHttpResponse response) {
        // 如果已經是 ApiResponse，就不再包一次
        if (body instanceof ApiResponse) {
            return body;
        }
        // 如果是 String，特別處理避免 JSON 轉換問題
        if (body instanceof String) {
            return "{\"code\":200,\"msg\":\"\",\"result\":\"" + body + "\"}";
        }
        // 其他情況一律包成 ApiResponse.success
        return ApiResponse.success("", body);
    }
}
