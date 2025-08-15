package com.snownilight.backtowork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snownilight.backtowork.common.ApiResponse;
import com.snownilight.backtowork.model.vo.ProductDetailVO;
import com.snownilight.backtowork.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ApiResponse<ProductDetailVO> getProductDetail(@PathVariable Long id) {
        return productService.getProductDetail(id)
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error(404, "Product not found"));
    }

    @PutMapping("/status/{id}")
    public ApiResponse<ProductDetailVO> updateProductStatus(@PathVariable Long id, @RequestBody Integer status) {
        return productService.updateProductStatus(id, status)
                .map(product -> ApiResponse.success("Product status updated successfully", product))
                .orElseGet(() -> ApiResponse.error(404, "Product not found or invalid status"));
    }
}