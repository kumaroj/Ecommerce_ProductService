package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(String id) throws ProductNotFoundException;

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto deleteProduct(String id);

    public GenericProductDto updateProduct(String id , GenericProductDto genericProductDto);
}
