package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.Cart;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Cart> findFirstCart();
}
