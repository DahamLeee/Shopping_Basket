package com.dahamleee.shopping_basket.exception;

public class CartProductNotFoundException extends RuntimeException {

    public CartProductNotFoundException(String message) {
        super(message);
    }
}
