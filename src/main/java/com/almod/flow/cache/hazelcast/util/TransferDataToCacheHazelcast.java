package com.almod.flow.cache.hazelcast.util;

import com.almod.flow.broker.type.activemq.ObjectMapperSingleton;
import com.almod.flow.cache.common.entity.CacheEntity;
import com.almod.flow.cache.common.util.TransferDataToCache;
import com.almod.flow.cache.hazelcast.config.ClientConfigHazelcast;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToCacheHazelcast extends TransferDataToCache {
    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @Override
    public void transferData(CacheEntity entity)  {
        String clientRequestString = null;
        try {
            clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(entity);
            IMap<Object, Object> map = clientConfigHazelcast.getMap();
            map.putAsync(entity.getUUID(), clientRequestString); // Асихронная вставка данных в мапу, что позволяет увеличить производительность в разы (Могут быть проблемы в согласованности данных)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
