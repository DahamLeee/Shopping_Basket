package com.dahamleee.shopping_basket.advice;

import com.dahamleee.shopping_basket.advice.result.ErrorResult;
import com.dahamleee.shopping_basket.exception.CartNotFoundException;
import com.dahamleee.shopping_basket.exception.CartProductCountMaximumException;
import com.dahamleee.shopping_basket.exception.CartProductCountMinimumException;
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
public class CartExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartNotFoundException.class)
    public ErrorResult cartNotFoundException(CartNotFoundException e) {
        log.error("[cartNotFoundException] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartProductCountMinimumException.class)
    public ErrorResult cartProductCountMinimumException(CartProductCountMinimumException e) {
        log.error("[cartProductCountMinimumException] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartProductCountMaximumException.class)
    public ErrorResult cartProductCountMaximumException(CartProductCountMaximumException e) {
        log.error("cartProductCountMaximumException] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
