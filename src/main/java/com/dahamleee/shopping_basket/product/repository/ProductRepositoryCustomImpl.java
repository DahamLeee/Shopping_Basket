package com.dahamleee.shopping_basket.product.repository;

import com.dahamleee.shopping_basket.product.dto.ProductDto;
import com.dahamleee.shopping_basket.product.dto.QProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.dahamleee.shopping_basket.product.domain.QProduct.product;

/*
Querydsl 및 순수 JPA 를 사용할 수 있는 구현체
 */
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductDto> search(Pageable pageable) {
        List<ProductDto> content = queryFactory
                .select(new QProductDto(
                        product.id.as("productId"),
                        product.name,
                        product.price,
                        product.quantity
                ))
                .from(product)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalSize = queryFactory
                .selectFrom(product)
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, totalSize);
    }
}
