package com.snownilight.backtowork.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snownilight.backtowork.mapper.ProductMapper;
import com.snownilight.backtowork.model.vo.ProductDetailVO;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Optional<ProductDetailVO> getProductDetail(Long id) {
        ProductDetailVO product = productMapper.selectProductDetailById(id);
        return Optional.ofNullable(product);
    }

    public Optional<ProductDetailVO> updateProductStatus(Long id, Integer status) {
        // 目前商品狀態只有0(下架)和1(上架)，如果status不是這兩個值，則返回空
        if (status == null || (status != 0 && status != 1)) {
            return Optional.empty();
        }
        boolean isSuccess = productMapper.updateProductStatus(id, status);
        if (isSuccess) {
            return getProductDetail(id);
        }
        return Optional.empty();
    }
}
