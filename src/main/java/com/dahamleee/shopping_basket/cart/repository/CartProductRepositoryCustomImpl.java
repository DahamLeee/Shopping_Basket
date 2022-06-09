package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.dahamleee.shopping_basket.cart.domain.QCartProduct.cartProduct;

@RequiredArgsConstructor
public class CartProductRepositoryCustomImpl implements CartProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CartProduct> findCartProductById(Long cartProductId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(cartProduct)
                        .where(cartProduct.id.eq(cartProductId))
                        .fetchOne());
    }

}
