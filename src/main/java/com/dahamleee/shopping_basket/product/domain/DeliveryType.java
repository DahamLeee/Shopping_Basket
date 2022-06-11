package com.dahamleee.shopping_basket.product.domain;

/**
 * 상품의 그룹을 담당할 enum 클래스
 */
public enum DeliveryType {

    SSG("쓱배송"),
    DAWN("새벽배송"),
    NORMAL("일반배송");

    private final String keyword;

    DeliveryType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
