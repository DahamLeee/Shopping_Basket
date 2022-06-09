package com.dahamleee.shopping_basket.cart.dto;

import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.dahamleee.shopping_basket.product.dto.ProductDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CartProductDto {

    private Long cartProductId; // PK
    private int cartPrice; // 판매가
    private int count; // 구매 수량
    private int totalPrice; // 구매 금액
    private boolean checked; // 체크 상태 여부

    private ProductDto productDto;

    @QueryProjection
    public CartProductDto(Long cartProductId, int cartPrice, int count, boolean checked, ProductDto productDto) {
        this.cartProductId = cartProductId;
        this.cartPrice = cartPrice;
        this.count = count;
        this.totalPrice = cartPrice * count;
        this.checked = checked;
        this.productDto = productDto;
    }

    public boolean isSSGProduct() {
        return productDto.getDeliveryType().equals(DeliveryType.SSG);
    }

    public boolean isDawnProduct() {
        return productDto.getDeliveryType().equals(DeliveryType.DAWN);
    }

    public boolean isNormalProduct() {
        return productDto.getDeliveryType().equals(DeliveryType.NORMAL);
    }

    public boolean isSoldOut() {
        return productDto.isSoldOut();
    }

}
