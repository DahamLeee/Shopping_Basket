package com.dahamleee.shopping_basket.cart.dto;

import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * Entity 자체를 View 에 전달하면 안되기 때문에 데이터 전달을 위한 클래스
 */
@Getter
public class CartDto {

    private final Long cartId; // PK
    private final List<CartProductDto> cartProducts; // 장바구니에 담겨있는 장바구니 상품 목록

    public CartDto(Long cartId, List<CartProductDto> cartProducts) {
        this.cartId = cartId;
        this.cartProducts = cartProducts;
    }

    // SSG 배송 상품만 리턴
    public List<CartProductDto> ssgProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isSSGProduct)
                .collect(toList());
    }

    // 새벽 배송 상품만 리턴
    public List<CartProductDto> dawnProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isDawnProduct)
                .collect(toList());
    }

    // 일반 배송 상품만 리턴
    public List<CartProductDto> normalProducts() {
        return cartProducts.stream()
                .filter(CartProductDto::isNormalProduct)
                .collect(toList());
    }

    // 장바구니에 담겨있는 상품의 총 가격을 구하는 메서드
    public int totalPrice() {
        // Check 가 하나도 안됐는지 확인
        if (isCheckedNotExists()) {
            // Check 가 하나도 안됐더라면 모든 장바구니의 총합
            return allCartProductsPrice();
        }

        // Check 가 하나라도 되어있다면 Check 되어 있는 장바구니의 총합
        return checkedCartProductsPrice();
    }

    // 체크된 상품이 하나라도 존재하는지 확인하는 메서드
    private boolean isCheckedNotExists() {
        return cartProducts.stream()
                .filter(CartProductDto::isChecked)
                .findAny()
                .isEmpty();
    }

    // 선택이 되어 있지 않기 때문에 모든 상품의 가격을 구함
    private int allCartProductsPrice() {
        return cartProducts.stream()
                .filter(cartProductDto -> !cartProductDto.isSoldOut())
                .mapToInt(CartProductDto::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    // 선택 되어 있는 상품만의 총 가격을 구함
    private int checkedCartProductsPrice() {
        return cartProducts.stream()
                .filter(CartProductDto::isChecked)
                .filter(cartProductDto -> !cartProductDto.isSoldOut())
                .mapToInt(CartProductDto::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    // 화면 상단의 navbar 에 존재하는 장바구니 아이콘에 몇 개의 상품이 담겨있는지 확인하는 메서드
    public int cartProductCount() {
        return cartProducts.size();
    }
}
