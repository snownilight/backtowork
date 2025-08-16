package com.snownilight.backtowork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.snownilight.backtowork.model.dto.CreateOrUpdateProduct;
import com.snownilight.backtowork.model.dto.PageRequest;
import com.snownilight.backtowork.model.dto.UpdateProductInfo;
import com.snownilight.backtowork.model.dto.UpdateProductItem;
import com.snownilight.backtowork.model.vo.ProductDetailVO;

@Mapper
public interface ProductMapper {
    ProductDetailVO selectProductDetailById(@Param("id") Long id);

    List<ProductDetailVO> selectProducts(PageRequest pageRequest);

    long countProducts(PageRequest pageRequest);

    boolean insertProduct(CreateOrUpdateProduct product);

    boolean insertProductItem(CreateOrUpdateProduct product);

    boolean updateProductInfo(UpdateProductInfo product);

    boolean updateProductItem(UpdateProductItem product);

    boolean updateProductStatus(@Param("id") Long id, @Param("status") Integer status);

    boolean deleteProductItemByProductId(@Param("id") Long id);

    boolean deleteProductById(@Param("id") Long id);
}
