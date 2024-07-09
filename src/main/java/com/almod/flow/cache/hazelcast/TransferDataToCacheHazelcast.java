package com.almod.flow.cache.hazelcast;

import com.almod.util.ObjectMapperSingleton;
import com.almod.store.entity.cache.CacheEntity;
import com.almod.flow.cache.TransferDataToCache;
import com.almod.config.ClientConfigHazelcast;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToCacheHazelcast")
public class TransferDataToCacheHazelcast extends TransferDataToCache {
    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @Override
    public void transferData(CacheEntity cacheEntity)  {
        String clientRequestString = null;
        try {
            clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(cacheEntity);
            IMap<Object, Object> map = clientConfigHazelcast.getMap();
            map.putAsync(cacheEntity.getUUID(), clientRequestString); // Асихронная вставка данных в мапу, что позволяет увеличить производительность в разы (Могут быть проблемы в согласованности данных)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
