package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.repository.CartRepository;
import com.dahamleee.shopping_basket.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart findFirstCart() {
        return cartRepository.findFirstCart()
                .orElseThrow(() -> new CartNotFoundException("장바구니가 정상적으로 생성되어있지 않습니다."));
    }

}
