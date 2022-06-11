package com.dahamleee.shopping_basket.exception;

public class CartProductNotFoundException extends RuntimeException {

    // 장바구니 상품이 조회되지 않을 경우의 예외
    public CartProductNotFoundException(String message) {
        super(message);
    }
}
