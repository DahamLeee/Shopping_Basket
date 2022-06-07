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
        p1 = Product.of("허쉬 초코멜로쿠키 45g", 600, 10, DeliveryType.SSG);
        p2 = Product.of("크리스피롤 12 곡 180g", 2_800, 5, DeliveryType.SSG);
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
        p1.addQuantity(10);
        p2.addQuantity(10);

        assertAll(
                () -> assertThat(p1.getQuantity()).isEqualTo(20),
                () -> assertThat(p2.getQuantity()).isEqualTo(15)
        );
    }

    @Test
    @DisplayName("상품의 재고 감소")
    void removeProductQuantity() {
        p1.removeQuantity(5);
        p2.removeQuantity(5);

        assertAll(
                () -> assertThat(p1.getQuantity()).isEqualTo(5),
                () -> assertThat(p2.getQuantity()).isZero()
        );
    }

    @Test
    @DisplayName("상품의 재고를 0 미만으로 감소시킬 시 예외 처리")
    void removeProductQuantityException() {
        assertThatThrownBy(() -> p1.removeQuantity(20))
                .isInstanceOf(NotEnoughProductException.class);
    }
}