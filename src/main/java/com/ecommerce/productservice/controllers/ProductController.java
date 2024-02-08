package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.services.ProductService;
import com.ecommerce.productservice.thirdpartyclientproductservice.fakestore.FakeStoreProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private FakeStoreProductService fakeStoreProductService;

    public ProductController(@Qualifier("FakeStoreProductService") FakeStoreProductService fakeStoreProductService,
                             @Qualifier("SelfProductServiceImpl") ProductService productservice
    ){
        this.productService = productservice;
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getproductbyId(@PathVariable("id")String id) throws ProductNotFoundException {
      GenericProductDto genericProductDto=  productService.getProductById(id);
        return genericProductDto;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
       return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteproductbyId(@PathVariable("id") String id){
        return  productService.deleteProduct(id);
    }


    @PutMapping("/{id}")
    public GenericProductDto updateproductbyId(@PathVariable("id") String id , @RequestBody GenericProductDto genericProductDto){
        return productService.updateProduct(id , genericProductDto);

    }

}
