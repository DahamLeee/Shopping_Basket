package com.dahamleee.shopping_basket.cart.service;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.dahamleee.shopping_basket.cart.repository.CartProductRepository;
import com.dahamleee.shopping_basket.exception.CartProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final CartProductRepository cartProductRepository;

    @Override
    @Transactional
    public void changeCartProductCheckStatus(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.changeCheckStatus();
    }

    @Override
    @Transactional
    public void removeCartProductsByIds(List<Long> cartProductIds) {
        cartProductRepository.removeCartProductsByIds(cartProductIds);
    }

    @Override
    @Transactional
    public void changeCartProductCount(Long cartProductId, int cartProductCount) {
        CartProduct findCartProduct = cartProductRepository.findCartProductById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("존재하지 않는 장바구니 상품입니다."));

        findCartProduct.inputCount(cartProductCount);
    }

    @Override
    @Transactional
    public void incrementCartProduct(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.increaseCount();
    }

    @Override
    @Transactional
    public void decrementCartProduct(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.decreaseCount();
    }

    @Override
    @Transactional
    public int order(List<Long> cartProductIds) {
        List<CartProduct> findCartProducts = cartProductRepository.findCartProductsByIdsWhereNotSoldOut(cartProductIds);

        if (findCartProducts.isEmpty()) {
            return 0;
        }

        int totalPrice = findCartProducts.stream()
                .mapToInt(CartProduct::calculateTotalCartProductPrice)
                .reduce(0, Integer::sum);

        cartProductRepository.removeCartProductsByCartProducts(findCartProducts);

        return totalPrice;
    }

    private CartProduct findOnlyCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("존재하지 않는 장바구니 상품입니다."));
    }
}
