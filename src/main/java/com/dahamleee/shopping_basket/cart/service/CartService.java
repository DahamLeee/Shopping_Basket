package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.dto.CartDto;

public interface CartService {

    CartDto findFirstCartDto();

    int addCartProduct(Long productId);

    int cartProductCount();
}
