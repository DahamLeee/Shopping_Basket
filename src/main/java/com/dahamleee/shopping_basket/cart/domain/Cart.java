package com.dahamleee.shopping_basket.cart.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member

    @Embedded
    private CartProducts cartProducts = new CartProducts();

    public int addCartProduct(CartProduct cartProduct) {
        int productCount = cartProducts.add(cartProduct);
        cartProduct.cart(this);
        return productCount;
    }

}
