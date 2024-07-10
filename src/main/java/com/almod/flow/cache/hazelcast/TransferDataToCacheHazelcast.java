package com.almod.flow.cache.hazelcast;

import com.almod.config.ClientConfigHazelcast;
import com.almod.flow.cache.TransferDataToCache;
import com.almod.store.entity.cache.CacheEntity;
import com.almod.util.ObjectMapperSingleton;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToCacheHazelcast")
public class TransferDataToCacheHazelcast extends TransferDataToCache {
    private final Logger logger = LoggerFactory.getLogger(TransferDataToCacheHazelcast.class);

    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @Override
    public void transferData(CacheEntity cacheEntity)  {
        String clientRequestString = null;
        try {
            logger.info("[{}] Try to send a data into the hazelcast", cacheEntity.getUUID());

            clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(cacheEntity);
            IMap<Object, Object> map = clientConfigHazelcast.getCacheMap();
            map.put(cacheEntity.getUUID(), clientRequestString);

            logger.info("[{}] Success inserted into the hazelcast", cacheEntity.getUUID());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
