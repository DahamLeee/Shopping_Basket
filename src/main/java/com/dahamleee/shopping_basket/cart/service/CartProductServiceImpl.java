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

    // 장바구니 상품의 체크 상태를 변경
    @Override
    @Transactional
    public void changeCartProductCheckStatus(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.changeCheckStatus();
    }

    // 장바구니 상품의 선택 삭제
    @Override
    @Transactional
    public void removeCartProductsByIds(List<Long> cartProductIds) {
        cartProductRepository.removeCartProductsByIds(cartProductIds);
    }

    // 장바구니 상품의 개수를 변환
    @Override
    @Transactional
    public void changeCartProductCount(Long cartProductId, int cartProductCount) {
        CartProduct findCartProduct = cartProductRepository.findCartProductById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("존재하지 않는 장바구니 상품입니다."));

        findCartProduct.inputCount(cartProductCount);
    }

    // 장바구니 상품의 개수를 1 증가
    @Override
    @Transactional
    public void incrementCartProduct(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.increaseCount();
    }

    // 장바구니 상품의 개수를 1 감소
    @Override
    @Transactional
    public void decrementCartProduct(Long cartProductId) {
        CartProduct findCartProduct = findOnlyCartProductById(cartProductId);
        findCartProduct.decreaseCount();
    }

    // 장바구니 상품의 주문 진행
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

    // PK 를 통해 장바구니 상품 조회
    private CartProduct findOnlyCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("존재하지 않는 장바구니 상품입니다."));
    }
}
