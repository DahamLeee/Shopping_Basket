package com.dahamleee.shopping_basket.exception;

public class CartProductCountMaximumException extends RuntimeException {

    // 장바구니 상품의 한도 예외
    public CartProductCountMaximumException(String message) {
        super(message);
    }
}
