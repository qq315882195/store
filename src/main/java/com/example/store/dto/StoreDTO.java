package com.example.store.dto;

import com.example.store.request.PageInfoParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StoreDTO extends PageInfoParam implements Serializable {

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
