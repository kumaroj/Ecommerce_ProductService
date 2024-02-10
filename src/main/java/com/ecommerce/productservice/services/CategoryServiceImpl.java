package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.CategoryDto;
import com.ecommerce.productservice.dtos.GenericProductDto;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("CategoryServiceImplementation")
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
      Optional<Category>categoryOptional = categoryRepository.findByName(categoryDto.getName()) ;
      if (categoryOptional.isEmpty()){
          Category category= new Category();
          category.setName(categoryDto.getName());
          category.setProductList(categoryDto.getProductList());
          categoryRepository.save(category);
          categoryDto = mapperForCategory(category);
      }
      else{
         categoryDto= mapperForCategory(categoryOptional.get());
      }

      return categoryDto;


    }

    @Override
    public List<String> getAllCategories() {

        List<String>categorynames = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.forEach(category -> {
            categorynames.add(category.getName());
        });
        return categorynames;

    }

    @Override
    public List<GenericProductDto> getSpecificCategoryProduct(String categoryName) {

        return null;
    }

    public CategoryDto mapperForCategory(Category category){
       CategoryDto categoryDto = new CategoryDto();
       categoryDto.setName(category.getName());
       categoryDto.setProductList(category.getProductList());
       return categoryDto;
    }
}
