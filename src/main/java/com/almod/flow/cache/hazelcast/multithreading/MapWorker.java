package com.almod.flow.cache.hazelcast.multithreading;

import com.almod.common.service.AbstractService;
import com.almod.flow.broker.type.activemq.ObjectMapperSingleton;
import com.almod.flow.cache.hazelcast.entity.HazelcastProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MapWorker extends AbstractWorker {

    private final Logger LOGGER = LoggerFactory.getLogger(MapWorker.class);

    private AbstractService abstractService;

    public MapWorker(Processor processor, AbstractService abstractService) {
        super(processor);
        this.abstractService = abstractService;
    }

    public void work() {
        IMap<Object, Object> map = processor.getClientConfigHazelcast().getMap();
        for(Map.Entry<Object, Object> mapEntry : map.entrySet()) {
            FencedLock keyLock = processor.getClientConfigHazelcast().getHazelcastInstance().getCPSubsystem().getLock(String.valueOf(mapEntry.getKey()));
            if(keyLock.tryLock()) {
                try {
                    keyProcessing(mapEntry.getKey());
                    map.remove(mapEntry.getKey());
                } finally {
                    try {
                        if(keyLock.isLockedByCurrentThread()) {
                            keyLock.unlock();
                            break;
                        }
                    } catch (Exception e) {
                        LOGGER.error("Error unlock key = {}", mapEntry.getKey());
                    }
                }
            }
        }
    }

    private void keyProcessing(Object key) {
        LOGGER.info("[{}] Try get data from cache", key);

        String object = (String) processor.getClientConfigHazelcast().getMap().get(key);
        LOGGER.info("[{}] Data: {}", key, object);

        try {
            if(!(object == null || object.isEmpty())) {
                HazelcastProduct hazelcastProduct = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(object, HazelcastProduct.class);
                abstractService.save(hazelcastProduct);

                LOGGER.info("[{}] Success inserted into the db", key);
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Error during deserialization for {}", object);
            e.printStackTrace();
        }
    }
}
