package com.example.store.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfoParam implements Serializable {
    private Integer page=1;
    private Integer pageSize=10;
}
