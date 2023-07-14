package com.springmvc.unid.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 응답 코드를 담은 CustomException
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;
}


