package com.snownilight.backtowork.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snownilight.backtowork.common.ApiResponse;
import com.snownilight.backtowork.model.dto.CreateOrUpdateProduct;
import com.snownilight.backtowork.model.dto.PageRequest;
import com.snownilight.backtowork.model.dto.UpdateProductInfo;
import com.snownilight.backtowork.model.dto.UpdateProductItem;
import com.snownilight.backtowork.model.vo.PageResult;
import com.snownilight.backtowork.model.vo.ProductDetailVO;
import com.snownilight.backtowork.service.ProductService;

import jakarta.validation.Valid;




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

    @PostMapping("/page")
    public ApiResponse<PageResult<List<ProductDetailVO>>> getProductByPage(@RequestBody PageRequest pageRequest) {
        PageResult<List<ProductDetailVO>> products = productService.getProducts(pageRequest)
                .orElseThrow(() -> new RuntimeException("Failed to fetch products"));
        return ApiResponse.success("Products fetched successfully", products);
    }
    
    @PostMapping("/insert")
    public ApiResponse<ProductDetailVO> insertProduct(@Valid @RequestBody CreateOrUpdateProduct product) {
        return productService.createProduct(product)
                .map(createdProduct -> ApiResponse.success("Product created successfully", createdProduct))
                .orElseGet(() -> ApiResponse.error(500, "Failed to create product"));
    }
    
    @PutMapping("/update")
    public ApiResponse<ProductDetailVO> updateProduct(@Valid @RequestBody CreateOrUpdateProduct product) {
        return productService.updateProduct(product)
                .map(updatedProduct -> ApiResponse.success("Product updated successfully", updatedProduct))
                .orElseGet(() -> ApiResponse.error(404, "Product not found or update failed"));
    }

    @PutMapping("/update/info")
    public ApiResponse<ProductDetailVO> updateProductInfo(@Valid @RequestBody UpdateProductInfo product) {
        return productService.updateProductInfo(product)
                .map(updatedProduct -> ApiResponse.success("Product info updated successfully", updatedProduct))
                .orElseGet(() -> ApiResponse.error(404, "Product not found or update failed"));
    }

    @PutMapping("/update/item")
    public ApiResponse<ProductDetailVO> updateProductItem(@Valid @RequestBody UpdateProductItem product) {
        return productService.updateProductItem(product)
                .map(updatedProduct -> ApiResponse.success("Product item updated successfully", updatedProduct))
                .orElseGet(() -> ApiResponse.error(404, "Product not found or update failed"));
    }

    @PutMapping("/status/{id}")
    public ApiResponse<ProductDetailVO> updateProductStatus(@PathVariable Long id, @RequestBody Integer status) {
        return productService.updateProductStatus(id, status)
                .map(product -> ApiResponse.success("Product status updated successfully", product))
                .orElseGet(() -> ApiResponse.error(404, "Product not found or invalid status"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ApiResponse.success("Product deleted successfully");
        } else {
            return ApiResponse.error(404, "Product not found or deletion failed");
        }
    }
}