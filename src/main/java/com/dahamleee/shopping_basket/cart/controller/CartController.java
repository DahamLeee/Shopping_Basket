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

    // 장바구니 화면으로 이동
    @GetMapping("/carts")
    public String carts(Model model) {
        CartDto findCart = cartService.findFirstCartDto();

        model.addAttribute("cart", findCart);

        return "cart/cart_list";
    }

    // 장바구니의 상품에 있는 체크박스를 클릭할 경우 체크 상태를 변경함
    @PostMapping("/carts/check/{cartProductId}")
    public String checkCart(@PathVariable Long cartProductId, Model model) {
        cartProductService.changeCartProductCheckStatus(cartProductId);

        CartDto findCart = cartService.findFirstCartDto();
        model.addAttribute("cart", findCart);

        // 전체 화면을 렌더링하는 대신 일부분만 렌더링
        return "cart/cart_list :: #cartProductTable";
    }

    // 장바구니 화면에서 선택되어 있는 상품을 장바구니에서 삭제함
    @PostMapping("/carts/cartProducts/out")
    public String removeCartProducts(@RequestParam(value = "checkedList[]") List<Long> cartProductIds) {
        cartProductService.removeCartProductsByIds(cartProductIds); // query

        return "redirect:/carts";
    }

    // 장바구니 상품의 개수를 수정함
    @PostMapping("/carts/cartProducts/{cartProductId}")
    public String changeCartProductsCount(
            @PathVariable Long cartProductId,
            @RequestParam("cartProductCount") int cartProductCount,
            Model model) {

        cartProductService.changeCartProductCount(cartProductId, cartProductCount);

        CartDto findCart = cartService.findFirstCartDto(); // result
        model.addAttribute("cart", findCart);

        return "cart/cart_list :: #cartProductTable";
    }

    // 장바구니 상품의 개수를 1 증가 시킴
    @PostMapping("/carts/cartProducts/{cartProductId}/increment")
    public String incrementCartProductCount(
            @PathVariable Long cartProductId,
            Model model) {

        cartProductService.incrementCartProduct(cartProductId);

        CartDto findCart = cartService.findFirstCartDto();
        model.addAttribute("cart", findCart);

        return "cart/cart_list :: #cartProductTable";
    }

    // 장바구니 상품의 개수를 1 감소 시킴
    @PostMapping("/carts/cartProducts/{cartProductId}/decrement")
    public String decrementCartProductCount(
            @PathVariable Long cartProductId,
            Model model) {

        cartProductService.decrementCartProduct(cartProductId);

        CartDto findCart = cartService.findFirstCartDto();
        model.addAttribute("cart", findCart);

        return "cart/cart_list :: #cartProductTable";
    }
}
