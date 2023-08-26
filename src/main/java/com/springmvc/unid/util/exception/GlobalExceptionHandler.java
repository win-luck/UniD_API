package com.springmvc.unid.util.exception;

import com.springmvc.unid.util.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ApiResponse<Void> handleCustomException(CustomException e) {
        log.error("CustomException: {}", e.getMessage());
        return ApiResponse.fail(e.getResponseCode());
    }
}
