package com.dahamleee.shopping_basket.product.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 상품 관련 엔티티
 * 상품명, 판매가, 수량을 멤버 변수로 갖는다.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int price;
    private int productQuantity;

    private Product(String productName, int price, int productQuantity) {
        this.productName = productName;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    public static Product of(String productName, int price, int productQuantity) {
        return new Product(productName, price, productQuantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
