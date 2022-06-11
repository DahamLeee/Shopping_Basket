package com.dahamleee.shopping_basket.advice;

import com.dahamleee.shopping_basket.advice.result.ErrorResult;
import com.dahamleee.shopping_basket.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 특정 예외를 등록 시키는 Advice [RestControllerAdvice]
 */
@Slf4j
@RestControllerAdvice
public class ProductExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResult productNotFoundException(ProductNotFoundException e) {
        log.error("[productNotFoundException] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

}
