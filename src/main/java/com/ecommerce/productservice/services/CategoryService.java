package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.CategoryDto;
import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;

import java.util.List;

public interface CategoryService {


    public CategoryDto createCategory(CategoryDto categoryDto);

    public List<String>getAllCategories();

    public List<GenericProductDto>getSpecificCategoryProduct(String categoryName);



}
