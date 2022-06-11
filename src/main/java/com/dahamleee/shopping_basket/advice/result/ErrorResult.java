package com.dahamleee.shopping_basket.advice.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Exception 발생 시 Code 와 예외 메시지를 담을 클래스
 */
@Data
@AllArgsConstructor
public class ErrorResult {

    private String code;
    private String message;
}
