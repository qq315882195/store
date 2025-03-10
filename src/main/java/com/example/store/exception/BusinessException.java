package com.example.store.exception;


import com.example.store.enums.StoreErrorEnum;

public class BusinessException extends RuntimeException{

    private String  code;
    private String  message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(String message, String code) {
        this.message= message;
        this.code=code;
    }
    public BusinessException(String message) {
        this.message= message;
        this.code= StoreErrorEnum.OPERATION_ERROR.getCode();
    }
}
