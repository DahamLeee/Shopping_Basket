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

    public void addCartProduct(CartProduct cartProduct) {
        cartProducts.add(cartProduct);
        cartProduct.cart(this);
    }

}
