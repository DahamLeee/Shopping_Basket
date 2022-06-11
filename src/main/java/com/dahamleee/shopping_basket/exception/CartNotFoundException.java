package com.dahamleee.shopping_basket.exception;

public class CartNotFoundException extends RuntimeException {

    // 장바구니가 조회되지 않을 경우 예외 처리
    public CartNotFoundException(String message) {
        super(message);
    }
}
