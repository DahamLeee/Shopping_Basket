package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.dto.CartDto;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Cart> findFirstCart();

    Optional<CartDto> findFirstCartDto();
}
