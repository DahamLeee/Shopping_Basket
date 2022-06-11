package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.response.CartResponse;
import com.dahamleee.shopping_basket.cart.service.CartProductService;
import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final CartProductService cartProductService;

    @PostMapping("/api/carts/{productId}")
    public ResponseEntity<CartResponse> addCartProduct(@PathVariable Long productId) {
        log.info("addCartProduct API CALL : {}", productId);

        int productCount = cartService.addCartProduct(productId);

        // API Response 생성
        return ResponseEntity.ok(CartResponse.createResponse("SUCCESS", productCount));
    }

    @GetMapping("/api/carts/cartProductCount")
    public ResponseEntity<Integer> cartProductCount() {
        int cartProductCount = cartService.cartProductCount();
        return ResponseEntity.ok(cartProductCount);
    }

    @PostMapping("/api/carts/cartProducts/order")
    public ResponseEntity<CartResponse> orderCartProduct(
            @RequestParam(value = "cartProductIds[]") List<Long> cartProductIds) {

        int totalPrice = cartProductService.order(cartProductIds);

        return ResponseEntity.ok(CartResponse.createOrderResponse("SUCCESS", totalPrice));
    }
}
