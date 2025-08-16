package com.snownilight.backtowork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snownilight.backtowork.mapper.ProductMapper;
import com.snownilight.backtowork.model.dto.CreateOrUpdateProduct;
import com.snownilight.backtowork.model.dto.PageRequest;
import com.snownilight.backtowork.model.dto.UpdateProductInfo;
import com.snownilight.backtowork.model.dto.UpdateProductItem;
import com.snownilight.backtowork.model.vo.PageResult;
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

    public Optional<PageResult<List<ProductDetailVO>>> getProducts(PageRequest pageRequest) {
        List<ProductDetailVO> products = productMapper.selectProducts(pageRequest);
        long totalCounts = productMapper.countProducts(pageRequest);
        int totalPages = (int) Math.ceil((double) totalCounts / pageRequest.getSize());
        PageResult<List<ProductDetailVO>> result = PageResult.<List<ProductDetailVO>>builder()
                .page(pageRequest.getPage())
                .size(pageRequest.getSize())
                .totalElements(totalCounts)
                .totalPages(totalPages)
                .content(products)
                .build();
        return Optional.ofNullable(result);
    }

    @Transactional
    public Optional<ProductDetailVO> createProduct(CreateOrUpdateProduct product) {
        // 新增商品基本資訊
        boolean isSuccess = productMapper.insertProduct(product);
        // 如果新增商品基本資訊成功，則新增商品項目資訊
        if (isSuccess) {
            isSuccess = productMapper.insertProductItem(product);
        }
        // 如果新增商品項目資訊成功，則返回新增的商品詳細資訊
        if (isSuccess) {
            return getProductDetail(product.getId());
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProductDetailVO> updateProduct(CreateOrUpdateProduct product) {
        UpdateProductInfo updateInfo = UpdateProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .build();
        // 更新商品基本資訊
        boolean isSuccess = productMapper.updateProductInfo(updateInfo);
        // 如果更新商品基本資訊成功，則更新商品項目資訊
        if (isSuccess) {
            UpdateProductItem updateItem = UpdateProductItem.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .status(product.getStatus())
                    .build();
            isSuccess = productMapper.updateProductItem(updateItem);
        }
        // 如果更新商品項目資訊成功，則返回更新後的商品詳細資訊
        if (isSuccess) {
            return getProductDetail(product.getId());
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProductDetailVO> updateProductInfo(UpdateProductInfo product) {
        // 更新商品基本資訊
        boolean isSuccess = productMapper.updateProductInfo(product);
        if (isSuccess) {
            return getProductDetail(product.getId());
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProductDetailVO> updateProductItem(UpdateProductItem product) {
        // 更新商品項目資訊
        boolean isSuccess = productMapper.updateProductItem(product);
        if (isSuccess) {
            return getProductDetail(product.getId());
        }
        return Optional.empty();
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

    @Transactional
    public boolean deleteProduct(Long id) {
        // 先刪除商品項目資訊
        boolean isSuccess = productMapper.deleteProductItemByProductId(id);
        // 如果刪除商品項目資訊成功，則刪除商品基本資訊
        if (isSuccess) {
            isSuccess = productMapper.deleteProductById(id);
        }
        return isSuccess;
    }
}
