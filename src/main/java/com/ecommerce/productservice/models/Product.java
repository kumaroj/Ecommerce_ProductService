package com.ecommerce.productservice.models;


import jakarta.persistence.Entity;

@Entity
public class Product extends  BaseModel{

    private  String name;
    private String description;

    private String image;

    private int price;

}
