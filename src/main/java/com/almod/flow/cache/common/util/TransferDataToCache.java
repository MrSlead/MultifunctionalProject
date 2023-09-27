package com.almod.flow.cache.common.util;

import com.almod.common.entity.AbstractEntity;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToCache implements AbstractTransferDataToCache<AbstractEntity> {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToCache.class);



    @Override
    public void transferData(AbstractEntity entity) {

    }
}
