package com.ecommerce.productservice.exceptions;
import com.ecommerce.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> productnotfoundException(ProductNotFoundException productNotFoundException) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
}
