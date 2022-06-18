package com.dahamleee.shopping_basket.product.repository;

import com.dahamleee.shopping_basket.product.domain.ProductSearchCondition;
import com.dahamleee.shopping_basket.product.dto.ProductDto;
import com.dahamleee.shopping_basket.product.dto.QProductDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dahamleee.shopping_basket.product.domain.QProduct.product;

/*
Querydsl 및 순수 JPA 를 사용할 수 있는 구현체
 */
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 전체 상품 조회
    @Override
    public Page<ProductDto> search(Pageable pageable, ProductSearchCondition productSearchCondition) {
        List<ProductDto> content = queryFactory
                .select(new QProductDto(
                        product.id.as("productId"),
                        product.name,
                        product.price,
                        product.quantity,
                        product.deliveryType
                ))
                .from(product)
                .where(productNameLike(productSearchCondition.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(product.count())
                .from(product)
                .where(productNameLike(productSearchCondition.getKeyword()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression productNameLike(String productName) {
        return StringUtils.hasText(productName) ? product.name.like("%" + productName + "%") : null;
    }
}
