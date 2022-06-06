package com.dahamleee.shopping_basket.cart.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.dahamleee.shopping_basket.product.domain.ConstProduct.*;
import static org.assertj.core.api.Assertions.*;

class CartProductsTest {

    @Test
    @DisplayName("장바구니에 새로운 상품을 추가함")
    void addCartProduct() {
        CartProduct cartProduct = CartProduct.createCartProduct(p1, 600);
        CartProducts cartProducts = new CartProducts();

        cartProducts.add(cartProduct);

        assertThat(cartProducts.getValue()).hasSize(1);
        assertThat(cartProducts.getValue()).containsExactly(cartProduct);
    }

    @Test
    @DisplayName("장바구니에 담겨있는 상품의 총 합계 구하기")
    void totalPrice() {
        CartProduct cartProduct1 = CartProduct.createCartProduct(p1, 600, 5);
        CartProduct cartProduct2 = CartProduct.createCartProduct(p2, 2_800, 5);
        CartProducts cartProducts = new CartProducts();

        cartProducts.add(cartProduct1);
        cartProducts.add(cartProduct2);

        assertThat(cartProducts.totalPrice()).isEqualTo(17_000);
    }
}