package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.services.ProductService;
import com.ecommerce.productservice.thirdpartyclientproductservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public ProductController(@Qualifier("FakeStoreProductService") FakeStoreProductServiceClient fakeStoreProductServiceClient,
                             @Qualifier("SelfProductServiceImpl") ProductService productservice
    ){
        this.productService = productservice;
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getproductbyId(@PathVariable("id")Long id) throws ProductNotFoundException {
      //GenericProductDto genericProductDto=  productService.getProductById(id);
        GenericProductDto genericProductDto = fakeStoreProductServiceClient.getproductbyId(id);
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
