package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.dahamleee.shopping_basket.cart.dto.CartDto;
import com.dahamleee.shopping_basket.cart.repository.CartRepository;
import com.dahamleee.shopping_basket.exception.CartNotFoundException;
import com.dahamleee.shopping_basket.exception.ProductNotFoundException;
import com.dahamleee.shopping_basket.product.domain.Product;
import com.dahamleee.shopping_basket.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartDto findFirstCartDto() {
        return cartRepository.findFirstCartDto()
                .orElseThrow(() -> new CartNotFoundException("장바구니가 정상적으로 생성되어있지 않습니다."));
    }

    @Override
    @Transactional
    public int addCartProduct(Long productId) {
        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("존재하지 않는 상품은 장바구니에 담을 수 없습니다."));

        Cart cart = cartRepository.findFirstCart()
                .orElseThrow(() -> new CartNotFoundException("장바구니가 존재하지 않습니다."));

        return cart.addCartProduct(CartProduct.createCartProduct(findProduct, findProduct.getPrice()));
    }

    @Override
    public int cartProductCount() {
        return findFirstCartDto().cartProductCount();
    }

}
