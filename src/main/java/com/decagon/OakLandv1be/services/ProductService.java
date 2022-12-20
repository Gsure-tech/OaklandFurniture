package com.decagon.OakLandv1be.services;

import com.decagon.OakLandv1be.dto.UpdateProductDto;
import com.decagon.OakLandv1be.entities.Product;
import com.decagon.OakLandv1be.utils.ApiResponse;
import com.decagon.OakLandv1be.dto.ProductCustResponseDto;


public interface ProductService {

    ApiResponse<Product> updateProduct(Long productId, UpdateProductDto updateproductDto);

    ProductCustResponseDto fetchASingleProduct(Long product_id);
}
