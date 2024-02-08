package com.ecommerce.productservice.thirdpartyclientproductservice.fakestore;

import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService{


    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
      this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;

    }


    public GenericProductDto getproductbyId(Long id) throws ProductNotFoundException {

      GenericProductDto  genericProductDto = converttogenericproductDto(fakeStoreProductServiceClient.getproductbyId(id));
      return genericProductDto;
    }


    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
     // fakeStoreProductServiceClient.createProduct(genericProductDto);
      return  converttogenericproductDto( fakeStoreProductServiceClient.createProduct(genericProductDto));
    }


    public List<GenericProductDto> getallProducts() {

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClient.getallProducts();
        fakeStoreProductDtos.forEach((fakeStoreProductDto) -> {
           GenericProductDto genericProductDto= converttogenericproductDto(fakeStoreProductDto);
            genericProductDtos.add(genericProductDto);
        });
        return genericProductDtos;
    }

    public GenericProductDto deleteProduct(Long id) {

       GenericProductDto genericProductDto = converttogenericproductDto(fakeStoreProductServiceClient.deleteProduct(id));
        return genericProductDto;
    }

    public GenericProductDto updateProduct(Long id , GenericProductDto genericProductDto) {
        GenericProductDto updatedProduct = converttogenericproductDto(fakeStoreProductServiceClient.updateProduct(id , genericProductDto));
        return updatedProduct;
    }

    private GenericProductDto converttogenericproductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto   genericProductDto = new GenericProductDto();
        genericProductDto.setId(String.valueOf(fakeStoreProductDto.getId()));
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return  genericProductDto;
    }

}