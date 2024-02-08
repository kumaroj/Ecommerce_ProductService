package com.ecommerce.productservice.models;

import jakarta.persistence.Entity;

@Entity

public class Price extends BaseModel{

    String currency;

    int price;

}
