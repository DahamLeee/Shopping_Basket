package com.dahamleee.shopping_basket.cart.domain;

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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void add(CartProduct cartProduct) {
        // 새로운 것을 추가하는 것인지, 이미 기존에 있는 것을 추가하는 것인지 비즈니스 로직을 짜야함
        cartProducts.add(cartProduct);
    }

    public int totalPrice() {
        return cartProducts.stream()
                .mapToInt(CartProduct::calculateTotalCartProductPrice)
                .reduce(0, Integer::sum);
    }

    public List<CartProduct> getCartProducts() {
        return unmodifiableList(cartProducts);
    }

}
