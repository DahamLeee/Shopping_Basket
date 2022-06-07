package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.dahamleee.shopping_basket.cart.domain.QCart.*;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cart> findFirstCart() {
        return Optional.ofNullable(
                queryFactory
                        .select(cart)
                        .from(cart)
                        .join(cart.cartProducts.value).fetchJoin()
                        .fetchFirst());
    }
}
