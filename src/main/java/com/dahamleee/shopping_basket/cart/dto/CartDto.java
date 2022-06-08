package com.dahamleee.shopping_basket.cart.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CartDto {

    private final Long cartId;
    private final List<CartProductDto> cartProducts;

    public CartDto(Long cartId, List<CartProductDto> cartProducts) {
        this.cartId = cartId;
        this.cartProducts = cartProducts;
    }

}
