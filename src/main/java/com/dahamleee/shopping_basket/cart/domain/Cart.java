package com.dahamleee.shopping_basket.cart.domain;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member

    @Embedded
    private CartProducts cartProducts = new CartProducts();

}
