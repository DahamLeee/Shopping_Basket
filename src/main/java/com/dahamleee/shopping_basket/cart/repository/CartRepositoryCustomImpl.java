package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.domain.QCart;
import com.dahamleee.shopping_basket.cart.dto.CartDto;
import com.dahamleee.shopping_basket.cart.dto.CartProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dahamleee.shopping_basket.cart.domain.QCart.*;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cart> findFirstCart() {
        return Optional.ofNullable(findCartFetchFirst());
    }

    @Override
    public Optional<CartDto> findFirstCartDto() {
        Cart cart = findCartFetchFirst();

        List<CartProductDto> cartProductDtos = cart.getCartProducts().getValue()
                .stream()
                .map(CartProductDto::new)
                .collect(Collectors.toList());

        return Optional.of(new CartDto(cart.getId(), cartProductDtos));
    }

    private Cart findCartFetchFirst() {
        return queryFactory
                .selectFrom(cart)
                .join(cart.cartProducts.value).fetchJoin()
                .fetchFirst();
    }
}
