package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreProductDto;
import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.thirdpartyclientproductservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements  ProductService {


    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
      this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;

    }

    @Override
    public GenericProductDto getproductbyId(Long id) throws ProductNotFoundException {

      GenericProductDto  genericProductDto = converttogenericproductDto(fakeStoreProductServiceClient.getproductbyId(id));
      return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
     // fakeStoreProductServiceClient.createProduct(genericProductDto);
      return  converttogenericproductDto( fakeStoreProductServiceClient.createProduct(genericProductDto));
    }

    @Override
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
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return  genericProductDto;
    }

}