package com.dahamleee.shopping_basket.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class WebMvcConfig {

    @PersistenceContext
    EntityManager em;

    // querydsl 을 사용하기 위해 JPAQueryFactory 를 빈으로 등록
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
    
}
