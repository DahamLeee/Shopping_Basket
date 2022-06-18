package com.dahamleee.shopping_basket.product.controller;

import com.dahamleee.shopping_basket.product.domain.ProductSearchCondition;
import com.dahamleee.shopping_basket.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 목록을 보여주는 메인 화면
     */
    @GetMapping("/")
    public String products(
            @PageableDefault(size = 1) Pageable pageable,
            @ModelAttribute("productSearchCondition") ProductSearchCondition productSearchCondition,
            Model model) {

        model.addAttribute("products", productService.search(pageable, productSearchCondition));

        return "product/product_list";
    }

    @GetMapping("/products/search")
    public String searchProduct(
            @PageableDefault Pageable pageable,
            @ModelAttribute("productSearchCondition") ProductSearchCondition productSearchCondition,
            Model model) {

        model.addAttribute("products", productService.search(pageable, productSearchCondition));

        return "product/product_list :: #productList";
    }
}
