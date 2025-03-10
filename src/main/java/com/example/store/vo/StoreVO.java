package com.example.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoreVO implements Serializable {

    private Long id;

    private Long created;
    private Long updated;
    private String code;
    private String name;
    private String address;
    private String longitude;
    private Long mechantId;
    private String latitude;
}
