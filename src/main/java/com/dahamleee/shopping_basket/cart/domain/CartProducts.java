package com.dahamleee.shopping_basket.cart.domain;

import com.dahamleee.shopping_basket.exception.CartProductNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProducts {

    private static final int ADD_CART_FIRST = 1;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> value = new ArrayList<>();

    // 사용자가 상품 옆에 있는 장바구니 버튼을 눌렀을 때 수행되는 메서드
    public int add(CartProduct cartProduct) {
        // 만약 장바구니에 이미 담겨있는 상품을 또 다시 담았다면
        if (isDuplicateCartProduct(cartProduct)) {
            // 이미 장바구니에 담겨있는 상품의 개수를 1 증가시킨다.
            return increaseCountCartProduct(cartProduct);
        }
        // 만약 그렇지 않다면 장바구니에 새로운 장바구니용 상품을 추가한다.
        value.add(cartProduct);

        return ADD_CART_FIRST;
    }

    // 중복된 상품이 있는지 확인하는 메서드
    private boolean isDuplicateCartProduct(CartProduct cartProduct) {
        return value.stream()
                .anyMatch(inCartProduct -> inCartProduct.isSameProduct(cartProduct));
    }

    // 중복된 상품의 개수를 1 올려주는 메서드
    private int increaseCountCartProduct(CartProduct cartProduct) {
        CartProduct findCartProduct = value.stream()
                .filter(inCartProduct -> inCartProduct.isSameProduct(cartProduct))
                .findFirst()
                .orElseThrow(() -> new CartProductNotFoundException("동일한 상품이 장바구니에 존재하지 않습니다."));

        findCartProduct.increaseCount();

        return findCartProduct.getCount();
    }

    // 장바구니에 담겨있는 모든 상품의 총액을 구하는 메서드
    public int totalPrice() {
        return value.stream()
                .mapToInt(CartProduct::calculateTotalCartProductPrice)
                .reduce(0, Integer::sum);
    }

    public List<CartProduct> getValue() {
        return unmodifiableList(value);
    }

    @Override
    public String toString() {
        return "CartProducts{" +
                "value=" + value +
                '}';
    }
}
