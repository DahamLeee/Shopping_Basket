package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    @PostMapping("/api/cart/{productId}")
    public Long addCartProduct(@PathVariable Long productId) {
        log.info("addCartProduct API CALL : {}", productId);

        cartService.addCartProduct(productId);

        // 장바구니에 담겼다는 메시지를 정상적으로 보내줘야겠죠?
        return productId;
    }
}
