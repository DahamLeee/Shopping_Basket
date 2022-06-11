package com.dahamleee.shopping_basket.cart.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * 장바구니를 담당할 Cart Entity (회원 기능이 없지만 회원 별로 1개씩 가짐)
 * {id, CartProducts}
 */
@Entity
@Getter
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member

    // OneToMany 의 형태로 장바구니 상품을 가지고 있지만 일급 콜렉션으로 분리
    @Embedded
    private CartProducts cartProducts = new CartProducts();

    // 사용자가 상품 목록에 있는 상품을 장바구니에 담을 때 사용되는 메서드
    // 연관관계 편의 메서드
    public int addCartProduct(CartProduct cartProduct) {
        int productCount = cartProducts.add(cartProduct);
        cartProduct.cart(this);
        return productCount;
    }

}
