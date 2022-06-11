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

    // 장바구니 상품을 PK 를 통해 조회 (ToOne 관계의 Product Entity 는 페치 조인)
    @Override
    public Optional<CartProduct> findCartProductById(Long cartProductId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(cartProduct)
                        .join(cartProduct.product, product).fetchJoin()
                        .where(cartProduct.id.eq(cartProductId))
                        .fetchOne());
    }

    // 선택된 장바구니 상품의 PK 를 전달받아 삭제
    @Override
    public void removeCartProductsByIds(List<Long> cartProductIds) {
        queryFactory.delete(cartProduct)
                .where(cartProduct.id.in(cartProductIds))
                .execute();
    }

    // 장바구니 목록에서 품절되지 않은 상품을 조회함
    @Override
    public List<CartProduct> findCartProductsByIdsWhereNotSoldOut(List<Long> cartProductIds) {
        return queryFactory.selectFrom(cartProduct)
                .join(cartProduct.product, product).fetchJoin()
                .where(cartProduct.id.in(cartProductIds))
                .where(cartProduct.product.quantity.ne(0))
                .fetch();
    }

    // 장바구니에서 주문을 진행한 후에 주문이 완료된 상품을 삭제함
    @Override
    public void removeCartProductsByCartProducts(List<CartProduct> cartProducts) {
        queryFactory.delete(cartProduct)
                .where(cartProduct.in(cartProducts))
                .execute();
    }

}
