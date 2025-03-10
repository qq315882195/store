package com.example.store.enums;

public enum StoreErrorEnum {
    OPERATION_ERROR("0001", "操作异常"),
    STORE_NOT_EXIST("0002","门店信息不存在" );

    private String code;
    private String desc;
    StoreErrorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public static StoreErrorEnum getEnumByCode(String code) {
        for (StoreErrorEnum storeErrorEnum : StoreErrorEnum.values()) {
            if (storeErrorEnum.getCode().equals(code)) {
                return storeErrorEnum;
            }
        }
        return null;
    }
}
