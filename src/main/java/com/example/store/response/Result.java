package com.example.store.response;

import java.io.Serializable;

public class Result implements Serializable {
    private Object data;
    private String message;
    private String code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result(Object data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
    public static Result success(Object data) {
        return new Result(data, "success", "0000");
    }
    public static Result success() {
        return new Result(null, "success", "0000");
    }
    public static Result error(String message, String code) {
        return new Result(null, message, code);
    }
    public static Result error(String message) {
        return new Result(null, message, "0001");
    }
}
