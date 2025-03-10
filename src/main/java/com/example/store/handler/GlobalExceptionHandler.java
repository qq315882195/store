package com.example.store.handler;

import com.example.store.enums.StoreErrorEnum;
import com.example.store.exception.BusinessException;
import com.example.store.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public final Result handleBusinessExceptions(BusinessException businessException, WebRequest request) {
        return Result.error( businessException.getMessage(),businessException.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public final Result handleException(Exception exception, WebRequest request) {
        return Result.error(StoreErrorEnum.OPERATION_ERROR.getDesc(), StoreErrorEnum.OPERATION_ERROR.getCode());
    }
}
