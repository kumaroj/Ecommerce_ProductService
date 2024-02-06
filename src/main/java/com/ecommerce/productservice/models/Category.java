package com.ecommerce.productservice.models;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Category extends  BaseModel{

    String name;
    List<Product> productList;
}
