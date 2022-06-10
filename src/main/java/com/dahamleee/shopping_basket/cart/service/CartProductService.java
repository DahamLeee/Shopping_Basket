package com.dahamleee.shopping_basket.cart.service;

import java.util.List;

public interface CartProductService {

    void changeCartProductCheckStatus(Long cartProductId);

    void removeCartProductsByIds(List<Long> cartProductIds);

    void changeCartProductCount(Long cartProductId, int cartProductCount);
}
