package com.dahamleee.shopping_basket.product.repository;

import com.dahamleee.shopping_basket.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
Querydsl 을 사용하기 위한 인터페이스
 */
public interface ProductRepositoryCustom {

    Page<ProductDto> search(Pageable pageable);

}
