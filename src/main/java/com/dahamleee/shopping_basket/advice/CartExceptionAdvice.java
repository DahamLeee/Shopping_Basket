package com.dahamleee.shopping_basket.advice;

import com.dahamleee.shopping_basket.advice.result.ErrorResult;
import com.dahamleee.shopping_basket.exception.CartNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CartExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartNotFoundException.class)
    public ErrorResult cartNotFoundException(CartNotFoundException e) {
        log.error("[cartNotFoundException] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
