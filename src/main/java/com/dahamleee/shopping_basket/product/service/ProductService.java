package com.dahamleee.shopping_basket.product.service;

import com.dahamleee.shopping_basket.product.domain.ProductSearchCondition;
import com.dahamleee.shopping_basket.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> search(Pageable pageable, ProductSearchCondition productSearchCondition);
}
