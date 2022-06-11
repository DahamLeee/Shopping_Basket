package com.dahamleee.shopping_basket;

import com.dahamleee.shopping_basket.cart.domain.Cart;
import com.dahamleee.shopping_basket.cart.domain.CartProduct;
import com.dahamleee.shopping_basket.product.domain.DeliveryType;
import com.dahamleee.shopping_basket.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 애플리케이션 시작 시 덤프 데이터를 추가하는 클래스
 */
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.addProducts();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        // DB 에 Product 데이터 추가
        public void addProducts() {
            Product hush = Product.of("허쉬 초코멜로쿠키 45g", 600, 10, DeliveryType.SSG);
            Product roll = Product.of("크리스피롤 12 곡 180g", 2_800, 5, DeliveryType.SSG);
            em.persist(hush);
            em.persist(roll);
            em.persist(Product.of("동물복지 유정란 15 개입 대란", 4_350, 0, DeliveryType.SSG));
            em.persist(Product.of("말랑카우 핸드워시 250ml", 2_600, 6, DeliveryType.SSG));
            em.persist(Product.of("삼립 미니꿀호떡 322g", 1_200, 4, DeliveryType.SSG));
            em.persist(Product.of("피코크 어랑손만두 어랑만두 700g", 6_400, 2, DeliveryType.DAWN));
            em.persist(Product.of("뺴뺴로바 아몬드아이스크림 4입", 2_800, 1, DeliveryType.DAWN));
            em.persist(Product.of("피코크 에이 클래스 우유 900ml", 2_500, 3, DeliveryType.DAWN));
            em.persist(Product.of("아삭달콤 방물토마토 500g", 4_500, 0, DeliveryType.DAWN));
            em.persist(Product.of("[롯데삼강] 돼지바 (70ml*6 입)", 3_000, 1, DeliveryType.DAWN));
            em.persist(Product.of("키즈 피크닉 팩 M", 65_000, 4, DeliveryType.NORMAL));
            em.persist(Product.of("이달의 원두 500g", 18_500, 4, DeliveryType.NORMAL));
            em.persist(Product.of("아쿠아 머그", 23_000, 7, DeliveryType.NORMAL));
            em.persist(Product.of("삼성전자 43 인치 스마트모니터", 480_000, 2, DeliveryType.NORMAL));
            em.persist(Product.of("나이키 헤리티지 스우시 캡", 25_000, 5, DeliveryType.NORMAL));

            addCartProducts(hush, roll);
        }

        // 추가 된 상품 중 일부를 장바구니에 담음
        private void addCartProducts(Product... products) {
            List<CartProduct> cartProducts = Arrays.stream(products)
                    .map(product -> CartProduct.createCartProduct(product, product.getPrice()))
                    .collect(Collectors.toList());

            // 유저 기능이 없기 때문에 Cart Entity 를 무조건적으로 하나 생성하고 시작하여야 함.
            Cart cart = new Cart();
            cartProducts.forEach(cart::addCartProduct);

            em.persist(cart);
        }
    }
}
