package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.response.CartResponse;
import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    @PostMapping("/api/cart/{productId}")
    public ResponseEntity<CartResponse> addCartProduct(@PathVariable Long productId) {
        log.info("addCartProduct API CALL : {}", productId);

        int productCount = cartService.addCartProduct(productId);

        // API Response 생성
        return ResponseEntity.ok(CartResponse.createResponse("SUCCESS", productCount));
    }
}
