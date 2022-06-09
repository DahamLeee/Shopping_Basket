package com.dahamleee.shopping_basket.cart.dto;

import com.dahamleee.shopping_basket.product.domain.DeliveryType;
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
                .filter(cartProductDto -> cartProductDto.getDeliveryType().equals(DeliveryType.SSG))
                .collect(toList());
    }

    public List<CartProductDto> dawnProducts() {
        return cartProducts.stream()
                .filter(cartProductDto -> cartProductDto.getDeliveryType().equals(DeliveryType.DAWN))
                .collect(toList());
    }

    public List<CartProductDto> normalProducts() {
        return cartProducts.stream()
                .filter(cartProductDto -> cartProductDto.getDeliveryType().equals(DeliveryType.NORMAL))
                .collect(toList());
    }

    public int totalPrice() {
        return cartProducts.stream()
                .mapToInt(CartProductDto::getTotalPrice)
                .reduce(0, Integer::sum);
    }
}
