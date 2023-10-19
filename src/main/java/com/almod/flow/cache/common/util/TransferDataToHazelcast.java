package com.almod.flow.cache.common.util;

import com.almod.common.entity.AbstractEntity;
import com.almod.flow.broker.type.activemq.ObjectMapperSingleton;
import com.almod.flow.cache.hazelcast.config.ClientConfigHazelcast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToHazelcast")
public class TransferDataToHazelcast implements AbstractTransferDataToCache<AbstractEntity> {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToHazelcast.class);

    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @Override
    public void transferData(AbstractEntity entity) throws JsonProcessingException {
        LOGGER.info("[{}] Try to send a data into the cache", entity.getUUID());

        String clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(entity);
        IMap<Object, Object> map = clientConfigHazelcast.getMap();
        map.putAsync(entity.getUUID(), clientRequestString); // Асихронная вставка данных в мапу, что позволяет увеличить производительность в разы (Могут быть проблемы в согласованности данных)

        LOGGER.info("[{}] Success inserted into the cache", entity.getUUID());
    }
}
