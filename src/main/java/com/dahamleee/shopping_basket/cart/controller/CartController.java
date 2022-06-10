package com.dahamleee.shopping_basket.cart.controller;

import com.dahamleee.shopping_basket.cart.dto.CartDto;
import com.dahamleee.shopping_basket.cart.service.CartProductService;
import com.dahamleee.shopping_basket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartProductService cartProductService;

    @GetMapping("/carts")
    public String carts(Model model) {
        CartDto findCart = cartService.findFirstCartDto();

        model.addAttribute("cart", findCart);

        return "cart/cart_list";
    }

    @PostMapping("/carts/check/{cartProductId}")
    public String checkCart(@PathVariable Long cartProductId, Model model) {
        cartProductService.changeCartProductCheckStatus(cartProductId);

        CartDto findCart = cartService.findFirstCartDto();
        model.addAttribute("cart", findCart);

        return "cart/cart_list :: #cartProductTable";
    }

    @PostMapping("/carts/cartProducts/out")
    public String removeCartProducts(@RequestParam(value = "checkedList[]") List<Long> cartProductIds) {
        cartProductService.removeCartProductsByIds(cartProductIds); // query

        return "redirect:/carts";
    }

}
