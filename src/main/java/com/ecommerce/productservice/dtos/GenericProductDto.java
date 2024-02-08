package com.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {


    private String id;
    public String title;
    public String description;

    public int price;

    public String category;
    public String image;

}
