package com.dahamleee.shopping_basket.cart.domain;

import com.dahamleee.shopping_basket.exception.CartProductCountMaximumException;
import com.dahamleee.shopping_basket.exception.CartProductCountMinimumException;
import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.dahamleee.shopping_basket.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class CartProductTest {

    private Product findProduct;
    private CartProduct cartProduct;

    @BeforeEach
    void setUp() {
        findProduct = Product.of("findProduct", 1_000, 5, DeliveryType.NORMAL);
        cartProduct = CartProduct.createCartProduct(findProduct, 1_000);
    }

    @Test
    @DisplayName("사용자가 특정 Product 를 장바구니에 담았을 때 CartProduct 생성 후 Cart 에 넣는다.")
    void createCartProduct() {
        // 사용자가 특정 Product 를 장바구니에 담을 때는 Id 값으로 findById 를 하고
        // CartProduct 를 생성한다.
        assertThat(cartProduct.calculateTotalCartProductPrice()).isEqualTo(1_000);
    }

    @Test
    @DisplayName("사용자가 장바구니에 담겨있는 CartProduct 의 구매 수를 1 증가시킨다.")
    void increaseCartProductCount() {
        cartProduct.increaseCount();
        assertThat(cartProduct.calculateTotalCartProductPrice()).isEqualTo(2_000);
    }

    @Test
    @DisplayName("사용자가 장바구니에 담겨있는 CartProduct 의 개수를 20 개 초과로 설정할 경우 예외처리")
    void increaseCartProductCountException() {
        cartProduct = CartProduct.createCartProduct(findProduct, 1_000, 20);
        assertThatThrownBy(() -> cartProduct.increaseCount())
                .isInstanceOf(CartProductCountMaximumException.class);
    }

    @Test
    @DisplayName("사용자가 장바구니에 담겨있는 CartProduct 의 개수를 1 감소시킨다.")
    void decreaseCartProductCount() {
        // 카트에 담겨있는 CartProduct 의 개수를 감소시킨다.
        // 현재 담겨 있는 CartProduct 의 개수가 1이기 때문에 바로 감소하면 예외가 발생하기 때문에 increase 호출 후 decrease 호출
        assertAll(
                () -> cartProduct.increaseCount(),
                () -> assertThat(cartProduct.calculateTotalCartProductPrice()).isEqualTo(2_000),
                () -> cartProduct.decreaseCount(),
                () -> assertThat(cartProduct.calculateTotalCartProductPrice()).isEqualTo(1_000)
        );
    }

    @Test
    @DisplayName("장바구니에 담겨있는 CartProduct 의 개수를 1 미만으로 감소시킬 때 예외처리")
    void decreaseCartProductCountException() {
        assertThatThrownBy(() -> cartProduct.decreaseCount())
                .isInstanceOf(CartProductCountMinimumException.class);
    }

    @Test
    @DisplayName("사용자가 장바구니에 담겨있는 CartProduct 의 개수를 입력하여 수정한다.")
    void inputCartProductRandomCount() {
        cartProduct.inputCount(5);
        assertThat(cartProduct.calculateTotalCartProductPrice()).isEqualTo(5_000);
    }

    @Test
    @DisplayName("사용자가 장바구니에 담겨있는 CartProduct 의 개수를 1 ~ 20 이외이 영역으로 입력시 예외처리.")
    void inputCartProductRandomCountException() {
        assertAll(
                () -> assertThatThrownBy(() -> cartProduct.inputCount(21))
                        .isInstanceOf(CartProductCountMaximumException.class),
                () -> assertThatThrownBy(() -> cartProduct.inputCount(-1))
                        .isInstanceOf(CartProductCountMinimumException.class)
        );
    }

    @Test
    @DisplayName("장바구니의 체크 상태를 변경하는 테스트")
    void changeCheckStatus() {
        CartProduct cartProduct = CartProduct.createCartProduct(Product.of("A1", 1_000, 1, DeliveryType.DAWN), 1_000);

        cartProduct.changeCheckStatus();

        assertThat(cartProduct.isChecked()).isTrue();
    }
}