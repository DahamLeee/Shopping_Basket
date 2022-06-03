package com.dahamleee.shopping_basket.product.domain;

import com.dahamleee.shopping_basket.exception.NotEnoughProductException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 상품 관련 엔티티
 * 상품명, 판매가, 수량을 멤버 변수로 갖는다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int quantity;

    private Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product of(String name, int price, int quantity) {
        return new Product(name, price, quantity);
    }

    /**
     * 상품의 재고 증가
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * 상품 구매 혹은 특정 이유로 인한 상품의 재고 감소
     */
    public void removeQuantity(int quantity) {
        int restQuantity = this.quantity - quantity;
        if (restQuantity < 0) {
            throw new NotEnoughProductException("상품의 현재 재고는 " + this.quantity + "개 입니다.");
        }
        this.quantity = restQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
