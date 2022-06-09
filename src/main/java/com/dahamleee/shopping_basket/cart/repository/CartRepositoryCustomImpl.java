package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.*;
import com.dahamleee.shopping_basket.cart.dto.CartDto;
import com.dahamleee.shopping_basket.cart.dto.CartProductDto;
import com.dahamleee.shopping_basket.cart.dto.QCartProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.dahamleee.shopping_basket.cart.domain.QCart.*;
import static com.dahamleee.shopping_basket.cart.domain.QCartProduct.*;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cart> findFirstCart() {
        return Optional.ofNullable(queryFactory
                .selectFrom(cart)
                .leftJoin(cart.cartProducts.value).fetchJoin()
                .fetchFirst());
    }

    @Override
    public Optional<CartDto> findFirstCartDto() {
        Cart cart = findCartFetchFirst();

        List<CartProductDto> cartProductDtos = findCartProductByCart(cart);

        return Optional.of(new CartDto(cart.getId(), cartProductDtos));
    }

    private Cart findCartFetchFirst() {
        return queryFactory
                .selectFrom(cart)
                .fetchFirst();
    }

    private List<CartProductDto> findCartProductByCart(Cart cart) {
        return queryFactory
                .select(new QCartProductDto(
                        cartProduct.id,
                        cartProduct.product.id,
                        cartProduct.product.name,
                        cartProduct.product.deliveryType,
                        cartProduct.cartPrice,
                        cartProduct.count,
                        cartProduct.checked))
                .from(cartProduct)
                .where(cartProduct.cart.eq(cart))
                .fetch();
    }
}
