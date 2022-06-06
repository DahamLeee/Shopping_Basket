package com.dahamleee.shopping_basket.cart.domain;

import com.dahamleee.shopping_basket.exception.CartProductCountMaximumException;
import com.dahamleee.shopping_basket.exception.CartProductCountMinimumException;
import com.dahamleee.shopping_basket.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct {

    private static final int MAX_CART_COUNT = 20;
    private static final int MIN_CART_COUNT = 1;

    @Id
    @Column(name = "cart_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int cartPrice;
    private int count;

    private CartProduct(Product product, int cartPrice, int count) {
        this.product = product;
        this.cartPrice = cartPrice;
        this.count = count;
    }

    private CartProduct(Product product, int cartPrice) {
        this(product, cartPrice, 1);
    }

    public static CartProduct createCartProduct(Product product, int cartPrice, int count) {
        return new CartProduct(product, cartPrice, count);
    }

    public static CartProduct createCartProduct(Product product, int cartPrice) {
        return new CartProduct(product, cartPrice);
    }

    // == 연관 관계 편이 메서드 == //
    public void cart(Cart cart) {
        this.cart = cart;
    }

    public int calculateTotalCartProductPrice() {
        return cartPrice * count;
    }

    public void increaseCount() {
        isMaxCartCount();
        this.count++;
    }

    private void isMaxCartCount() {
        if (this.count >= MAX_CART_COUNT) {
            throw new CartProductCountMaximumException("최대 주문 수량은 20개 입니다.");
        }
    }

    public void decreaseCount() {
        isMinCartCount();
        this.count--;
    }

    private void isMinCartCount() {
        if (this.count <= MIN_CART_COUNT) {
            throw new CartProductCountMinimumException("최소 주문 수량은 1개 입니다.");
        }
    }

    public void inputCount(int count) {
        isMaxCartCount(count);
        isMinCartCount(count);
        this.count = count;
    }

    private void isMaxCartCount(int count) {
        if (count > MAX_CART_COUNT) {
            throw new CartProductCountMaximumException("최대 주문 수량은 20개 입니다.");
        }
    }

    private void isMinCartCount(int count) {
        if (count < MIN_CART_COUNT) {
            throw new CartProductCountMinimumException("최소 주문 수량은 1개 입니다.");
        }
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", cart=" + cart +
                ", cartPrice=" + cartPrice +
                ", count=" + count +
                '}';
    }
}
