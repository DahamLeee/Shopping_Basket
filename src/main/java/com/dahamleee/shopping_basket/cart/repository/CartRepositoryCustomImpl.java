package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.*;
import com.dahamleee.shopping_basket.cart.dto.CartDto;
import com.dahamleee.shopping_basket.cart.dto.CartProductDto;
import com.dahamleee.shopping_basket.cart.dto.QCartProductDto;
import com.dahamleee.shopping_basket.product.dto.QProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.dahamleee.shopping_basket.cart.domain.QCart.*;
import static com.dahamleee.shopping_basket.cart.domain.QCartProduct.*;

@RequiredArgsConstructor
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 현재 유저 기능이 없기 때문에 하나의 Cart Entity 만을 사용하기 때문에
    // 맨 처음 Cart 를 조회함
    @Override
    public Optional<Cart> findFirstCart() {
        return Optional.ofNullable(queryFactory
                .selectFrom(cart)
                .leftJoin(cart.cartProducts.value).fetchJoin()
                .fetchFirst());
    }

    // View 에 넘기기 위해서 Cart Entity 를 CartDto 형식으로 조회함
    @Override
    public Optional<CartDto> findFirstCartDto() {
        Cart cart = findCartFetchFirst();

        List<CartProductDto> cartProductDtos = findCartProductByCart(cart);

        return Optional.of(new CartDto(cart.getId(), cartProductDtos));
    }

    // 맨 처음 Cart 를 조회함
    private Cart findCartFetchFirst() {
        return queryFactory
                .selectFrom(cart)
                .fetchFirst();
    }

    // Cart 에 담겨있는 장바구니 상품을 모두 조회함
    private List<CartProductDto> findCartProductByCart(Cart cart) {
        return queryFactory
                .select(new QCartProductDto(
                        cartProduct.id,
                        cartProduct.cartPrice,
                        cartProduct.count,
                        cartProduct.checked,
                        new QProductDto(
                                cartProduct.product.id,
                                cartProduct.product.name,
                                cartProduct.product.price,
                                cartProduct.product.quantity,
                                cartProduct.product.deliveryType)))
                .from(cartProduct)
                .where(cartProduct.cart.eq(cart))
                .fetch();
    }
}
