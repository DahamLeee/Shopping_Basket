package com.dahamleee.shopping_basket.product.domain;

import com.dahamleee.shopping_basket.exception.NotEnoughProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("상품의 재고 증가")
    void addProductQuantity() {
        p1.addProductQuantity(10);
        p2.addProductQuantity(10);

        assertAll(
                () -> assertThat(p1.getProductQuantity()).isEqualTo(20),
                () -> assertThat(p2.getProductQuantity()).isEqualTo(15)
        );
    }

    @Test
    @DisplayName("상품의 재고 감소")
    void removeProductQuantity() {
        p1.removeProductQuantity(5);
        p2.removeProductQuantity(5);

        assertAll(
                () -> assertThat(p1.getProductQuantity()).isEqualTo(5),
                () -> assertThat(p2.getProductQuantity()).isZero()
        );
    }

    @Test
    @DisplayName("상품의 재고를 0 미만으로 감소시킬 시 예외 처리")
    void removeProductQuantityException() {
        assertThatThrownBy(() -> p1.removeProductQuantity(20))
                .isInstanceOf(NotEnoughProductException.class);
    }
}