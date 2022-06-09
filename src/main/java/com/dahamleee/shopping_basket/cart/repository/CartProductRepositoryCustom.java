package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;

import java.util.Optional;

public interface CartProductRepositoryCustom {

    Optional<CartProduct> findCartProductById(Long cartProductId);
}