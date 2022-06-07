package com.dahamleee.shopping_basket.product.domain;

public enum DeliveryType {

    SSG("쓱배송"),
    DAWN("새벽배송"),
    NORMAL("일반배송");

    private final String keyword;

    DeliveryType(String keyword) {
        this.keyword = keyword;
    }
}
