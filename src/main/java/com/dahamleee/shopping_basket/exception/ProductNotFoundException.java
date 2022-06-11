package com.dahamleee.shopping_basket.exception;

public class ProductNotFoundException extends RuntimeException {

    // 상품이 조회되지 않을 경우의 예외
    public ProductNotFoundException(String message) {
        super(message);
    }
}
