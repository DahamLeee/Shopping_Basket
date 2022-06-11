package com.dahamleee.shopping_basket.exception;

public class NotEnoughProductException extends RuntimeException {

    // 상품의 개수가 부족할 경우 예외
    public NotEnoughProductException(String message) {
        super(message);
    }
}
