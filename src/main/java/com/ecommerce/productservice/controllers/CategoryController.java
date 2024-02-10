package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.services.CategoryService;
import com.ecommerce.productservice.thirdpartyclientproductservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public CategoryController(@Qualifier("CategoryServiceImplementation") CategoryService categoryService ,
                              @Qualifier("FakeStoreProductService") FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.categoryService = categoryService;
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return fakeStoreProductServiceClient.getAllCategories();
    }

    @GetMapping("/category")
    public List<GenericProductDto> getProductsFromCategory(@RequestParam String categoryName){

       return fakeStoreProductServiceClient.getSpecificCategoryProduct(categoryName);
    }

}
