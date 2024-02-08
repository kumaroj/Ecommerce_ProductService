package com.ecommerce.productservice.thirdpartyclientproductservice.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    public String title;
    public String description;

    public int price;

    public String Category;
    public String image;

}
