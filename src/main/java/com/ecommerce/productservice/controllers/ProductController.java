package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.ExceptionDto;
import com.ecommerce.productservice.dtos.FakeStoreProductDto;
import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productservice){
        this.productService = productservice;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
       return productService.getallProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getproductbyId(@PathVariable("id")Long id) throws ProductNotFoundException {
      GenericProductDto genericProductDto=  productService.getproductbyId(id);
        return genericProductDto;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
       return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteproductbyId(@PathVariable("id") Long id){
        return  productService.deleteProduct(id);
    }


    @PutMapping("/{id}")
    public GenericProductDto updateproductbyId(@PathVariable("id") Long id , @RequestBody GenericProductDto genericProductDto){
        return productService.updateProduct(id , genericProductDto);

    }

}
