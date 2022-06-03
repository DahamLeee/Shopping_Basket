package com.dahamleee.shopping_basket.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product p1;
    private Product p2;

    @BeforeEach
    void setUp() {
        p1 = Product.of("허쉬 초코멜로쿠키 45g", 600, 10);
        p2 = Product.of("크리스피롤 12 곡 180g", 2_800, 5);
    }

    @Test
    @DisplayName("상품 엔티티 인스턴스 생성 확인")
    void create() {
        System.out.println(p1);
        System.out.println(p2);
    }

}