package com.example.store.controller;

import com.example.store.dto.StoreDTO;
import com.example.store.response.PageInfoResponse;
import com.example.store.response.Result;
import com.example.store.sevice.StoreService;
import com.example.store.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping("/getStoreList")
    public Result getStoreList(@RequestBody StoreDTO storeDTO){
        List<StoreVO> storeVOList=storeService.getStoreList(storeDTO);
        return Result.success(storeVOList);
    }

    //分页查询门店
    @RequestMapping("/getStoreListByPage")
    public Result getStoreListByPage(@RequestBody StoreDTO storeDTO){
        PageInfoResponse response=storeService.getStoreListByPage(storeDTO);
        return Result.success(response);
    }

    //新增门店
    @RequestMapping("/addStore")
    public Result addStore(@RequestBody StoreDTO storeDTO){
        storeService.addStore(storeDTO);
        return Result.success();
    }

    //删除门店
    @RequestMapping("/deleteStore")
    public Result deleteStore(@RequestBody StoreDTO storeDTO){
        storeService.deleteStore(storeDTO);
        return Result.success();
    }

    //修改门店
    @RequestMapping("/updateStore")
    public Result updateStore(@RequestBody StoreDTO storeDTO){
        storeService.updateStore(storeDTO);
        return Result.success();
    }

    //查询门店
    @RequestMapping("/getStore")
    public Result getStore(@RequestBody StoreDTO storeDTO){
        StoreVO storeVO=storeService.getStore(storeDTO);
        return Result.success(storeVO);
    }
}
