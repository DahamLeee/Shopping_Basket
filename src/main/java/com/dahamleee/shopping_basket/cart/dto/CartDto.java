package com.dahamleee.shopping_basket.cart.dto;

import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
public class CartDto {

    private final Long cartId;
    private final List<CartProductDto> cartProducts;

    public CartDto(Long cartId, List<CartProductDto> cartProducts) {
        this.cartId = cartId;
        this.cartProducts = cartProducts;
    }

    public List<CartProductDto> ssgProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isSSGProduct)
                .collect(toList());
    }

    public List<CartProductDto> dawnProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isDawnProduct)
                .collect(toList());
    }

    public List<CartProductDto> normalProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isNormalProduct)
                .collect(toList());
    }

    public int totalPrice() {
        // Check 가 하나도 안됐는지 확인
        if (isCheckedNotExists()) {
            // Check 가 하나도 안됐더라면 모든 장바구니의 총합
            return allCartProductsPrice();
        }

        // Check 가 하나라도 되어있다면 Check 되어 있는 장바구니의 총합
        return checkedCartProductsPrice();
    }

    private boolean isCheckedNotExists() {
        return cartProducts.stream()
                .filter(CartProductDto::isChecked)
                .findAny()
                .isEmpty();
    }

    private int allCartProductsPrice() {
        return cartProducts.stream()
                .filter(cartProductDto -> !cartProductDto.isSoldOut())
                .mapToInt(CartProductDto::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    private int checkedCartProductsPrice() {
        return cartProducts.stream()
                .filter(CartProductDto::isChecked)
                .filter(cartProductDto -> !cartProductDto.isSoldOut())
                .mapToInt(CartProductDto::getTotalPrice)
                .reduce(0, Integer::sum);
    }
}
