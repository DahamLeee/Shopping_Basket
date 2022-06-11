package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.response.CartResponse;
import com.dahamleee.shopping_basket.cart.service.CartProductService;
import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final CartProductService cartProductService;

    // 상품 목록 화면에서 장바구니 버튼을 클릭하여 장바구니에 상품을 담음
    @PostMapping("/api/carts/{productId}")
    public ResponseEntity<CartResponse> addCartProduct(@PathVariable Long productId) {
        log.info("addCartProduct API CALL : {}", productId);

        int productCount = cartService.addCartProduct(productId);

        // API Response 생성
        return ResponseEntity.ok(CartResponse.createResponse("SUCCESS", productCount));
    }

    // 상단의 navbar 에 존재하는 장바구니의 개수를 알려주는 API
    @GetMapping("/api/carts/cartProductCount")
    public ResponseEntity<Integer> cartProductCount() {
        int cartProductCount = cartService.cartProductCount();
        return ResponseEntity.ok(cartProductCount);
    }

    // 장바구니에서 상품을 주문하는 API
    @PostMapping("/api/carts/cartProducts/order")
    public ResponseEntity<CartResponse> orderCartProduct(
            @RequestParam(value = "cartProductIds[]") List<Long> cartProductIds) {

        int totalPrice = cartProductService.order(cartProductIds);

        return ResponseEntity.ok(CartResponse.createOrderResponse("SUCCESS", totalPrice));
    }
}
