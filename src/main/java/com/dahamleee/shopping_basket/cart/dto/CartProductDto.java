package com.dahamleee.shopping_basket.cart.dto;

import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CartProductDto {

    private Long cartProductId; // PK
    private Long productId; // 장바구니에 담겨있는 상품의 PK

    private String name; // 상품명
    private DeliveryType deliveryType;
    private int cartPrice; // 판매가
    private int count; // 구매 수량
    private int totalPrice; // 구매 금액

    @QueryProjection
    public CartProductDto(Long cartProductId, Long productId, String name, DeliveryType deliveryType, int cartPrice, int count) {
        this.cartProductId = cartProductId;
        this.productId = productId;
        this.name = name;
        this.deliveryType = deliveryType;
        this.cartPrice = cartPrice;
        this.count = count;
        this.totalPrice = cartPrice * count;
    }

}
