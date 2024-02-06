package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreProductDto;
import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDto getproductbyId(Long id) throws ProductNotFoundException;

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getallProducts();

    public GenericProductDto deleteProduct(Long id);

    public GenericProductDto updateProduct(Long id , GenericProductDto genericProductDto);
}
