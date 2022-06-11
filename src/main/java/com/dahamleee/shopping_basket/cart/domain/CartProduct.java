package com.dahamleee.shopping_basket.cart.domain;

import com.dahamleee.shopping_basket.exception.CartProductCountMaximumException;
import com.dahamleee.shopping_basket.exception.CartProductCountMinimumException;
import com.dahamleee.shopping_basket.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 장바구니에 담기는 장바구니 상품 엔티티
 * {id, Product[어떤 상품을], Cart[어떤 카트에], cartPrice[판매 가격], count[주문할 수량], checked[체크 박스 상태]}
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct {

    private static final int MIN_CART_COUNT = 1;
    private static final int MAX_CART_COUNT = 20;

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
    private boolean checked;

    private CartProduct(Product product, int cartPrice, int count) {
        this.product = product;
        this.cartPrice = cartPrice;
        this.count = count;
        this.checked = false;
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

    // 장바구니 상품 하나의 총 가격
    public int calculateTotalCartProductPrice() {
        return cartPrice * count;
    }

    // 장바구니 상품의 개수를 1 증가시킴
    public void increaseCount() {
        isMaxCartCount();
        this.count++;
    }

    // 최대 주문 수량 validation
    private void isMaxCartCount() {
        if (this.count >= MAX_CART_COUNT) {
            throw new CartProductCountMaximumException("최대 주문 수량은 20개 입니다.");
        }
    }

    // 장바구니 상품의 개수를 1 감소시킴
    public void decreaseCount() {
        isMinCartCount();
        this.count--;
    }

    // 최소 주문 수량 validation
    private void isMinCartCount() {
        if (this.count <= MIN_CART_COUNT) {
            throw new CartProductCountMinimumException("최소 주문 수량은 1개 입니다.");
        }
    }

    // 상품의 주문 개수를 수정함
    public void inputCount(int count) {
        isMaxCartCount(count);
        isMinCartCount(count);
        product.maxQuantity(count);
        this.count = count;
    }

    // count 를 통해 최대 주문 수량 validation
    private void isMaxCartCount(int count) {
        if (count > MAX_CART_COUNT) {
            throw new CartProductCountMaximumException("최대 주문 수량은 20개 입니다.");
        }
    }

    // count 를 통해 최소 주문 수량 validation
    private void isMinCartCount(int count) {
        if (count < MIN_CART_COUNT) {
            throw new CartProductCountMinimumException("최소 주문 수량은 1개 입니다.");
        }
    }

    // 상품 목록 화면에서 기존에 장바구니에 담겨있는 상품을 다시 담으려고 장바구니 버튼을 클릭했을 때
    // 중복 상품일 경우를 확인하기 위한 메서드
    public boolean isSameProduct(CartProduct cartProduct) {
        return this.product.equals(cartProduct.product);
    }

    // 체크 박스의 상태를 기존 상태에서 다른 상태로 변환하기 위한 메서드
    public void changeCheckStatus() {
        this.checked = !checked;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", cart=" + cart +
                ", product=" + product +
                ", cartPrice=" + cartPrice +
                ", count=" + count +
                '}';
    }
}
