package com.dahamleee.shopping_basket.product.service;

import com.dahamleee.shopping_basket.product.domain.ProductSearchCondition;
import com.dahamleee.shopping_basket.product.dto.ProductDto;
import com.dahamleee.shopping_basket.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
    Controller 와 Repository 영역을 연결 짓는 서비스 레이어
    Domain 영역에 메시지를 보내는 Thin Layer
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // 전체 상품 조회
    @Override
    public Page<ProductDto> search(Pageable pageable, ProductSearchCondition productSearchCondition) {
        return productRepository.search(pageable, productSearchCondition);
    }
}
