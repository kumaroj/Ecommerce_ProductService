package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements  ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductServiceImpl(RestTemplateBuilder restTemplateBuilder, ProductRepository productRepository,
                                  CategoryRepository categoryRepository) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) throws ProductNotFoundException{

        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if (product.isEmpty())
            throw  new ProductNotFoundException("Product with id " + id +" is not present");

       GenericProductDto genericProductDto= mapperForProductDto(product.get());
        return genericProductDto;
    }



    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        Product product =  new Product();

        Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDto.getCategory());
        if (optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(genericProductDto.getCategory());
            product.setCategory(category);
        }
        else{
            product.setCategory(optionalCategory.get());
        }
        product.setName(genericProductDto.getTitle());
        product.setProductPrice(genericProductDto.getPrice());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());

        productRepository.save(product);
       return mapperForProductDto(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
       List<Product>productslist = productRepository.findAll();
       List<GenericProductDto>genericProductDtoList = new ArrayList<>();
       productslist.forEach((p )->{
        GenericProductDto  genericProductDto= mapperForProductDto(p);
        genericProductDtoList.add(genericProductDto);
       });

        return genericProductDtoList;
    }

    @Override
    public GenericProductDto deleteProduct(String id) {
     productRepository.deleteById(UUID.fromString(id));
        return null;
    }

    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {
        Product product = null;
       Optional<Product>optionalProduct = productRepository.findById(UUID.fromString(id));
       if (!optionalProduct.isEmpty()){
          product = optionalProduct.get();
          product.setName(genericProductDto.getTitle());
          product.setProductPrice(genericProductDto.getPrice());
          product.setDescription(genericProductDto.getDescription());
          product.setImage(genericProductDto.getImage());
          productRepository.save(product);
       }
      return mapperForProductDto(product);
    }

    public GenericProductDto mapperForProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId().toString());
        genericProductDto.setTitle(product.getName());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getProductPrice());
        return genericProductDto;
    }
}
