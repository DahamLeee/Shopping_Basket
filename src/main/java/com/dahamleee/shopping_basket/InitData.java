package com.dahamleee.shopping_basket;

import com.dahamleee.shopping_basket.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

        public void addProducts() {
            em.persist(Product.of("허쉬 초코멜로쿠키 45g", 600, 10));
            em.persist(Product.of("크리스피롤 12 곡 180g", 2_800, 5));
            em.persist(Product.of("동물복지 유정란 15 개입 대란", 4_350, 0));
            em.persist(Product.of("말랑카우 핸드워시 250ml", 2_600, 6));
            em.persist(Product.of("삼립 미니꿀호떡 322g", 1_200, 4));
            em.persist(Product.of("피코크 어랑손만두 어랑만두 700g", 6_400, 2));
            em.persist(Product.of("뺴뺴로바 아몬드아이스크림 4입", 2_800, 1));
            em.persist(Product.of("피코크 에이 클래스 우유 900ml", 2_500, 3));
            em.persist(Product.of("아삭달콤 방물토마토 500g", 4_500, 0));
            em.persist(Product.of("[롯데삼강] 돼지바 (70ml*6 입)", 3_000, 1));
            em.persist(Product.of("키즈 피크닉 팩 M", 65_000, 4));
            em.persist(Product.of("이달의 원두 500g", 18_500, 4));
            em.persist(Product.of("아쿠아 머그", 23_000, 7));
            em.persist(Product.of("삼성전자 43 인치 스마트모니터", 480_000, 2));
            em.persist(Product.of("나이키 헤리티지 스우시 캡", 25_000, 5));
        }
    }
}
