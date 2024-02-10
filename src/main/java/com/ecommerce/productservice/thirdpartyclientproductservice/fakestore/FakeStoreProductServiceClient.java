package com.ecommerce.productservice.thirdpartyclientproductservice.fakestore;


import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("FakeStoreProductService")
@Primary
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;


    private String FakeStoreBaseUrl;
    private  String FakeStoreProductPathUrl;
    private String FakeStoreCategoryPathUrl;
    private String FakeStoreProductCategoryPath;
    private String SpecificProductUrl;
    private String CreateProductUrl ;
    private String CategoryPath ;
    private String productCategoryPath ;
    private final CategoryRepository categoryRepository;


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         CategoryRepository categoryRepository,
                                         @Value("${FakeStore.BaseUrl}")String FakeStoreBaseUrl,
                                         @Value("${FakeStore.ProductPath}")String FakeStoreProductPathUrl,
                                         @Value("${FakeStore.CategoryPath}")String FakeStoreCategoryPathUrl,
                                         @Value("${FakeStore.Category.ProductPath}")String FakeStoreProductCategoryPath
                                         ){
        this.restTemplateBuilder = restTemplateBuilder;
        this.categoryRepository = categoryRepository;
        this.FakeStoreBaseUrl = FakeStoreBaseUrl;
        this.FakeStoreProductPathUrl = FakeStoreProductPathUrl;
        this.FakeStoreCategoryPathUrl = FakeStoreCategoryPathUrl;
        this.FakeStoreProductCategoryPath = FakeStoreProductCategoryPath;
        this.SpecificProductUrl = FakeStoreBaseUrl + FakeStoreProductPathUrl + "/{id}";
        this.CreateProductUrl = FakeStoreBaseUrl + FakeStoreProductPathUrl;
        this.CategoryPath = FakeStoreBaseUrl + FakeStoreProductPathUrl+FakeStoreCategoryPathUrl;
        this.productCategoryPath = FakeStoreBaseUrl + FakeStoreProductPathUrl+ FakeStoreProductCategoryPath +"/{categoryName}";
    }

    private RestTemplate getrestTemplate(){

        return  restTemplateBuilder.build();
    }

    public GenericProductDto getproductbyId(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate= getrestTemplate();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(SpecificProductUrl,
                FakeStoreProductDto.class, id);
        if (responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product Not Found ");
        }
       return converttogenericproductDto(responseEntity.getBody());
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = getrestTemplate();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(FakeStoreBaseUrl + FakeStoreProductPathUrl
                , genericProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();

    }


    public List<FakeStoreProductDto> getallProducts() {
        RestTemplate restTemplate= getrestTemplate();
        // ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(getproductUrl, FakeStoreProductDto[].class);
        //ResponseEntity<List<GenericProductDto>> responseEntity;
        //FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(FakeStoreBaseUrl + FakeStoreProductPathUrl,
                FakeStoreProductDto[].class);

       // List<GenericProductDto> genericProductDtos = new ArrayList<>();

       /* ResponseEntity<List<GenericProductDto>> responseEntity =  restTemplate.exchange(createproductrequestUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<GenericProductDto>>() { });*/

     /*   List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.stream(responseEntity.getBody()).toList();
        fakeStoreProductDtos.forEach((fakeStoreProductDto) -> {
            GenericProductDto genericProductDto= converttogenericproductDto(fakeStoreProductDto);
            genericProductDtos.add(genericProductDto);
        });*/
        return Arrays.stream(responseEntity.getBody()).toList();
    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate= getrestTemplate();
      /*  RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(getproductUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);*/

        ResponseEntity<FakeStoreProductDto> responseEntity  =  restTemplate.exchange(FakeStoreBaseUrl + FakeStoreProductPathUrl + "{id}" , HttpMethod.DELETE,
                null, FakeStoreProductDto.class, id);

       // GenericProductDto genericProductDto = converttogenericproductDto(responseEntity.getBody());
        return responseEntity.getBody();
    }


    public FakeStoreProductDto updateProduct(Long id , GenericProductDto genericProductDto) {
        RestTemplate restTemplate= getrestTemplate();
    /*   ResponseEntity<FakeStoreProductDto>response = restTemplate.exchange(getproductUrl , HttpMethod.PUT ,null,
               FakeStoreProductDto.class , id);*/

      /*  ResponseEntity<FakeStoreProductDto>response = restTemplate.exchange(getproductUrl, HttpMethod.PUT, null,
                new ParameterizedTypeReference<FakeStoreProductDto>() {
                },genericProductDto , id);*/

        ResponseEntity<FakeStoreProductDto>response =  restTemplate.exchange(FakeStoreBaseUrl + FakeStoreProductPathUrl , HttpMethod.PUT,
                null,FakeStoreProductDto.class , id , genericProductDto );

       /* RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity  = restTemplate.execute(getproductUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, id);*/
        //return responseEntity.getBody();

       // GenericProductDto updatedProduct = converttogenericproductDto(response.getBody());
        return response.getBody();
    }

    public List<String> getAllCategories(){
        RestTemplate restTemplate = getrestTemplate();
        ResponseEntity<String[]> categoryList = restTemplate.getForEntity(CategoryPath, String[].class);
        return Arrays.stream(categoryList.getBody()).toList();
    }

    public List<GenericProductDto> getSpecificCategoryProduct(String categoryName){
        RestTemplate restTemplate = getrestTemplate();
        ResponseEntity<FakeStoreProductDto[]>response =restTemplate.
                getForEntity(productCategoryPath,FakeStoreProductDto[].class ,categoryName);

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        Arrays.stream(response.getBody()).toList().forEach(fakeStoreProductDto -> {
            genericProductDtoList.add(converttogenericproductDto(fakeStoreProductDto));
        });
        return genericProductDtoList;

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
