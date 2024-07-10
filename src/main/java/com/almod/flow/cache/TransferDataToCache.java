package com.almod.flow.cache;

import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.cache.CacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToCache")
public class TransferDataToCache implements AbstractTransferData<CacheEntity> {
    private final Logger logger = LoggerFactory.getLogger(TransferDataToCache.class);

    private TransferDataToCacheFactory transferDataToCacheFactory;

    @Autowired
    public void setTransferDataToCacheFactory(TransferDataToCacheFactory transferDataToCacheFactory) {
        this.transferDataToCacheFactory = transferDataToCacheFactory;
    }

    @Override
    public void transferData(CacheEntity cacheEntity) {
        TransferDataToCache instance = transferDataToCacheFactory.getDependenceForTransferData(cacheEntity);
        instance.transferData(cacheEntity);
    }
}
