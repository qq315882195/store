package com.example.store.converter;


import com.example.store.dto.StoreDTO;
import com.example.store.pojo.Store;
import com.example.store.vo.StoreVO;

public class StoreConverter {

    /**
     * DTO转换成POJO
     */
    public static Store toPO(StoreDTO storeDTO) {
        Store store = new Store();
        store.setCode(storeDTO.getCode());
        store.setAddress(storeDTO.getAddress());
        store.setName(storeDTO.getName());
        store.setLongitude(storeDTO.getLongitude());
        store.setLatitude(storeDTO.getLatitude());
        store.setMechantId(storeDTO.getMechantId());
        return store;
    }

    public static StoreVO toVO(Store store) {
        StoreVO storeVO = new StoreVO();
        storeVO.setLongitude(store.getLongitude());
        storeVO.setLatitude(store.getLatitude());
        storeVO.setAddress(store.getAddress());
        storeVO.setName(store.getName());
        storeVO.setCode(store.getCode());
        storeVO.setId(store.getId());
        storeVO.setCreated(store.getCreated());
        storeVO.setUpdated(store.getUpdated());
        return storeVO;
    }
}
