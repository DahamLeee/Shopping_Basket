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

    /**
     * 상품의 재고 증가
     */
    public void addProductQuantity(int quantity) {
        this.productQuantity += quantity;
    }

    /**
     * 상품 구매 혹은 특정 이유로 인한 상품의 재고 감소
     */
    public void removeProductQuantity(int quantity) {
        int restQuantity = this.productQuantity - quantity;
        if (restQuantity < 0) {
            throw new NotEnoughProductException("상품의 현재 재고는 " + this.productQuantity + "개 입니다.");
        }
        this.productQuantity = restQuantity;
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
