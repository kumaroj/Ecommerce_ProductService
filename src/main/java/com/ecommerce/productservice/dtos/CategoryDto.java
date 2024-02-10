package com.ecommerce.productservice.dtos;

import com.ecommerce.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

   private String name;

   private List<Product> productList;
}
