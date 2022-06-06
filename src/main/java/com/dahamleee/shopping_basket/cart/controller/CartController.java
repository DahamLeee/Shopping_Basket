package com.dahamleee.shopping_basket.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/carts")
    public String carts(Model model) {
        model.addAttribute("hello", "hello");
        return "cart/cart_list";
    }

}
