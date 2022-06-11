package com.dahamleee.shopping_basket.cart.response;

import lombok.Data;

@Data
public class CartResponse {

    private String code;
    private String message;

    private CartResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CartResponse createResponse(String code, int productCount) {
        if (productCount == 1) {
            return new CartResponse(code, "장바구니에 상품이 담겼습니다.");
        }
        return new CartResponse(code, "한 번 더 담으셨네요!\n" +
                "장바구니 수량이 " + productCount + "개가 되었습니다.");
    }

    public static CartResponse createOrderResponse(String code, int totalPrice) {
        if (totalPrice == 0) {
            return new CartResponse(code, "죄송합니다.\n" +
                    "상품 품절 등의 사유로 주문 가능한 상품이 없습니다.");
        }
        return new CartResponse(code, "구매하신 총 금액은 " + totalPrice + "원 입니다.");
    }
}
