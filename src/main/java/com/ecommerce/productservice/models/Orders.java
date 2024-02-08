package com.ecommerce.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders extends  BaseModel{

    @ManyToMany
    @JoinTable(
     name = "product_orders",
      joinColumns = @JoinColumn(name="orderId"),
      inverseJoinColumns =  @JoinColumn(name = "productId"))
    private List<Product> productList;
}
