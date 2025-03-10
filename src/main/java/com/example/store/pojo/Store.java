package com.example.store.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Store implements Serializable {
    @TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
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
