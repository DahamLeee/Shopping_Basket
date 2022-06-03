package com.dahamleee.shopping_basket.product.dto;

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

    private Long productId;
    private String name;
    private int price;
    private int quantity;
    
    @QueryProjection
    public ProductDto(Long productId, String name, int price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
