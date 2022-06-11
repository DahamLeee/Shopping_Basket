package com.dahamleee.shopping_basket.cart.repository;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.dahamleee.shopping_basket.cart.domain.QCartProduct.cartProduct;
import static com.dahamleee.shopping_basket.product.domain.QProduct.product;

@RequiredArgsConstructor
public class CartProductRepositoryCustomImpl implements CartProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CartProduct> findCartProductById(Long cartProductId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(cartProduct)
                        .join(cartProduct.product, product).fetchJoin()
                        .where(cartProduct.id.eq(cartProductId))
                        .fetchOne());
    }

    @Override
    public void removeCartProductsByIds(List<Long> cartProductIds) {
        queryFactory.delete(cartProduct)
                .where(cartProduct.id.in(cartProductIds))
                .execute();
    }

    @Override
    public List<CartProduct> findCartProductsByIdsWhereNotSoldOut(List<Long> cartProductIds) {
        return queryFactory.selectFrom(cartProduct)
                .join(cartProduct.product, product).fetchJoin()
                .where(cartProduct.id.in(cartProductIds))
                .where(cartProduct.product.quantity.ne(0))
                .fetch();
    }

    @Override
    public void removeCartProductsByCartProducts(List<CartProduct> cartProducts) {
        queryFactory.delete(cartProduct)
                .where(cartProduct.in(cartProducts))
                .execute();
    }

}
