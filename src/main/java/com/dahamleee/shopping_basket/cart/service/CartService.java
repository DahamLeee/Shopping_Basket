package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.domain.Cart;

public interface CartService {

    Cart findFirstCart();

    int addCartProduct(Long productId);
}
