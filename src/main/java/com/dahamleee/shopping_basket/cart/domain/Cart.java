package com.dahamleee.shopping_basket.cart.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
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
