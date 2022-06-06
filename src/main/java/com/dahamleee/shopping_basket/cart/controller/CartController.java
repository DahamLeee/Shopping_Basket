package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public String carts(Model model) {
        Cart findCart = cartService.findFirstCart();

        model.addAttribute("cart", findCart);

        return "cart/cart_list";
    }

}
