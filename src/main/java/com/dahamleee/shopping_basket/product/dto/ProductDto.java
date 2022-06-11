package com.dahamleee.shopping_basket.product.dto;

import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Querydsl 을 위한 DTO 객체
 * API 송수신 및 화면에 전달하는 데이터는 Entity 가 아닌 요청, 응답 전용 클래스를 사용해야 하는데,
 * 상품 화면에 노출 시킬 ProductDto 클래스
 */
@Getter
@NoArgsConstructor
public class ProductDto {

    private static final int NO_STOCK = 0;

    private Long productId;
    private String name;
    private int price;
    private int quantity;
    private DeliveryType deliveryType;
    
    @QueryProjection
    public ProductDto(Long productId, String name, int price, int quantity, DeliveryType deliveryType) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.deliveryType = deliveryType;
    }

    public boolean isSoldOut() {
        return quantity == NO_STOCK;
    }
}
