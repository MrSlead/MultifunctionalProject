package com.almod.flow.cache;

import com.almod.flow.AbstractTransferDataFactory;
import com.almod.store.entity.cache.CacheEntity;
import com.almod.store.entity.cache.hazelcast.HazelcastEntity;
import com.almod.util.SpringApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToCacheFactory implements AbstractTransferDataFactory<CacheEntity> {
    @Override
    public TransferDataToCache getDependenceForTransferData(CacheEntity entity) {
        try {
            TransferDataToCache instance;
            if(entity instanceof HazelcastEntity) {
                instance = (TransferDataToCache) SpringApplicationContext.getContext().getBean("TransferDataToCacheHazelcast");

                return instance;
            }
            else
                throw new Exception("Unknown entity type: " + entity.getClass().getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
