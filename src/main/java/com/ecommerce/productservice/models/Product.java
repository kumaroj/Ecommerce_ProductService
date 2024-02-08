package com.ecommerce.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
public class Product extends  BaseModel{

    private  String name;
    private String description;

    private String image;

    private int productPrice;

    @OneToOne
    @Cascade({CascadeType.PERSIST,CascadeType.REMOVE})
    private Price price;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    @Cascade({CascadeType.PERSIST})
    private Category category;

}
