package com.snownilight.backtowork.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.snownilight.backtowork.model.vo.ProductDetailVO;

@Mapper
public interface ProductMapper {
    ProductDetailVO selectProductDetailById(@Param("id") Long id);

    boolean updateProductStatus(@Param("id") Long id, @Param("status") Integer status);
}
