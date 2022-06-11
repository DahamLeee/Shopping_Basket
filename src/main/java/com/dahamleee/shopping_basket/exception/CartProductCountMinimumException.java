package com.dahamleee.shopping_basket.exception;

public class CartProductCountMinimumException extends RuntimeException {

    // 장바구니 상품의 최소 개수 예외
    public CartProductCountMinimumException(String message) {
        super(message);
    }
}
