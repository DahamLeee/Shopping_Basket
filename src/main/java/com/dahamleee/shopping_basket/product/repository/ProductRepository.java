package com.dahamleee.shopping_basket.product.repository;

import com.dahamleee.shopping_basket.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Spring Data JPA 사용을 위한 인터페이스
 */
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
