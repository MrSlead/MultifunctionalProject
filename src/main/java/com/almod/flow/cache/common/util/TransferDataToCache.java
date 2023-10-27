package com.almod.flow.cache.common.util;

import com.almod.flow.cache.common.entity.CacheEntity;
import com.almod.flow.cache.common.entity.HazelcastEntity;
import com.almod.flow.cache.type.hazelcast.util.TransferDataToCacheFactory;
import com.almod.flow.cache.type.hazelcast.util.TransferDataToCacheHazelcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToCache")
public class TransferDataToCache implements AbstractTransferDataToCache {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToCache.class);

    private TransferDataToCacheFactory transferDataToCacheFactory;

    @Autowired
    public void setTransferDataToCacheFactory(TransferDataToCacheFactory transferDataToCacheFactory) {
        this.transferDataToCacheFactory = transferDataToCacheFactory;
    }

    @Override
    public void transferData(CacheEntity entity) throws Exception {
        LOGGER.info("[{}] Try to send a data into the cache", entity.getUUID());

        if(entity instanceof HazelcastEntity) {
            TransferDataToCacheHazelcast instance = (TransferDataToCacheHazelcast) transferDataToCacheFactory.getDependenceForTransferData(entity);
            instance.transferData(entity);
        } else throw new Exception("Unknown entity");

        LOGGER.info("[{}] Success inserted into the cache", entity.getUUID());
    }
}
