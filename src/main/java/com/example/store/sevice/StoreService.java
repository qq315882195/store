package com.example.store.sevice;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.store.converter.StoreConverter;
import com.example.store.dto.StoreDTO;
import com.example.store.enums.StoreErrorEnum;
import com.example.store.exception.BusinessException;
import com.example.store.mapper.StoreMapper;
import com.example.store.pojo.Store;
import com.example.store.response.PageInfoResponse;
import com.example.store.vo.StoreVO;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StoreService extends ServiceImpl<StoreMapper, Store> {

    @Autowired
    private RedissonClient redissonClient;

    public List<StoreVO> getStoreList(StoreDTO storeDTO) {
        LambdaQueryWrapper<Store> queryWrapper= new LambdaQueryWrapper<>();
        //根据条件查询
        queryWrapper.eq(!StringUtils.isEmpty(storeDTO.getName()),Store::getName,storeDTO.getName());
        queryWrapper.eq(!StringUtils.isEmpty(storeDTO.getCode()),Store::getCode,storeDTO.getCode());
        queryWrapper.eq(storeDTO.getMechantId()!=null,Store::getMechantId,storeDTO.getMechantId());
        List<Store> stores = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(stores)){
            return null;
        }
        List<StoreVO> storeVOS = Lists.newArrayList();
        stores.forEach(store -> {
            StoreVO storeVO = StoreConverter.toVO(store);
            storeVOS.add(storeVO);
        });
        return storeVOS;
    }

    public void addStore(StoreDTO storeDTO) {
        Store store = StoreConverter.toPO(storeDTO);
        this.baseMapper.insert(store);
        redissonClient.getBucket("store#"+store.getId()).set(store);
    }

    public void deleteStore(StoreDTO storeDTO) {
        this.baseMapper.deleteById(storeDTO.getId());
        redissonClient.getBucket("store#"+storeDTO.getId()).delete();
    }

    public void updateStore(StoreDTO storeDTO) {
        Store store = StoreConverter.toPO(storeDTO);
        this.baseMapper.updateById(store);
        redissonClient.getBucket("store#"+store.getId()).set(store);
    }

    public StoreVO getStore(StoreDTO storeDTO) {
        RBucket<Object> bucket = redissonClient.getBucket("store#" + storeDTO.getId());
        if (bucket.isExists()){
            return StoreConverter.toVO( (Store) bucket.get());
        }
        Store store = this.baseMapper.selectById(storeDTO.getId());
        if (store == null){
            throw new BusinessException(StoreErrorEnum.STORE_NOT_EXIST.getDesc(),StoreErrorEnum.STORE_NOT_EXIST.getCode());
        }
        bucket.set(store);
        return  StoreConverter.toVO(store) ;
    }

    public PageInfoResponse getStoreListByPage(StoreDTO storeDTO) {
        Page<Store> page = new Page<>(storeDTO.getPage(), storeDTO.getPageSize());
        LambdaQueryWrapper<Store> queryWrapper= new LambdaQueryWrapper<>();
        //根据条件查询
        queryWrapper.eq(storeDTO.getMechantId()!=null,Store::getMechantId,storeDTO.getMechantId());

        queryWrapper.like(!StringUtils.isEmpty(storeDTO.getName()),Store::getName,storeDTO.getName());
        queryWrapper.like(!StringUtils.isEmpty(storeDTO.getCode()),Store::getCode,storeDTO.getCode());
        IPage<Store> storePage = this.baseMapper.selectPage(page, queryWrapper);
        PageInfoResponse pageInfoResponse = new PageInfoResponse();
        pageInfoResponse.setCurrent(storePage.getCurrent());
        pageInfoResponse.setSize(storePage.getSize());
        pageInfoResponse.setTotal(storePage.getTotal());
        pageInfoResponse.setPages(storePage.getPages());
        if(!CollectionUtils.isEmpty(storePage.getRecords())){
            List<StoreVO> storeVOS = Lists.newArrayList();
            storePage.getRecords().forEach(store -> {
                StoreVO storeVO = StoreConverter.toVO(store);
                storeVOS.add(storeVO);
            });
            pageInfoResponse.setRecords(storeVOS);
        }
        return pageInfoResponse;
    }
}
