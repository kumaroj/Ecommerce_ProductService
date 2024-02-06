package com.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {

   private String errorMessage;

   private HttpStatus code;

    public ExceptionDto(String errorMessage, HttpStatus code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }
}
