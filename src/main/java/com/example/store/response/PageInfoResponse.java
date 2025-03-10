package com.example.store.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfoResponse<T> implements Serializable {
    private List<T> records;
    private long total;
    private long size;
    private long current;
}
