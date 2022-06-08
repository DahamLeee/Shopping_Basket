package com.dahamleee.shopping_basket.cart.dto;

import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.dahamleee.shopping_basket.product.domain.Product;
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

    public CartProductDto(CartProduct cartProduct) {
        Product findProduct = cartProduct.getProduct();

        this.cartProductId = cartProduct.getId();

        this.productId = findProduct.getId();
        this.name = findProduct.getName();
        this.deliveryType = findProduct.getDeliveryType();

        this.cartPrice = cartProduct.getCartPrice();
        this.count = cartProduct.getCount();
        this.totalPrice = this.cartPrice * this.count;
    }

}
