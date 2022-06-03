package com.dahamleee.shopping_basket.product.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/")
    public String products() {

        return "product/product_list";
    }
}
