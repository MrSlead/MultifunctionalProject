package com.almod.flow.cache.type.hazelcast.util;

import com.almod.flow.cache.common.entity.CacheEntity;
import com.almod.flow.cache.common.entity.HazelcastEntity;
import com.almod.flow.cache.common.util.TransferDataToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToCacheFactory {
    private TransferDataToCacheHazelcast transferDataToCacheHazelcast;

    @Autowired
    @Lazy
    public TransferDataToCacheFactory(TransferDataToCacheHazelcast transferDataToCacheHazelcast) {
        this.transferDataToCacheHazelcast = transferDataToCacheHazelcast;
    }

    public TransferDataToCache getDependenceForTransferData(CacheEntity entity) throws Exception {
        if(entity instanceof HazelcastEntity) {
            if(transferDataToCacheHazelcast == null) transferDataToCacheHazelcast = new TransferDataToCacheHazelcast();
            return transferDataToCacheHazelcast;
        }
        else throw new Exception();
    }
}
