package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.dahamleee.shopping_basket.cart.repository.CartProductRepository;
import com.dahamleee.shopping_basket.exception.CartProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final CartProductRepository cartProductRepository;

    @Override
    @Transactional
    public void changeCartProductCheckStatus(Long cartProductId) {
        CartProduct cartProduct = cartProductRepository.findCartProductById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("존재하지 않는 장바구니 상품입니다."));

        cartProduct.changeCheckStatus();
    }
}