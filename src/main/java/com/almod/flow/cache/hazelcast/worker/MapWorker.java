package com.almod.flow.cache.hazelcast.worker;

import com.almod.store.service.AbstractService;
import com.almod.util.ObjectMapperSingleton;
import com.almod.store.entity.cache.hazelcast.HazelcastProductEntity;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MapWorker extends AbstractWorker {

    private final Logger logger = LoggerFactory.getLogger(MapWorker.class);

    private AbstractService abstractService;

    public MapWorker(Processor processor, AbstractService abstractService) {
        super(processor);
        this.abstractService = abstractService;
    }

    public void work() {
        IMap<Object, Object> map = processor.getClientConfigHazelcast().getCacheMap();
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
                        logger.error("Error unlock key = {}", mapEntry.getKey());
                    }
                }
            }
        }
    }

    private void keyProcessing(Object key) {
        logger.info("[{}] Try get data from cache", key);

        String object = (String) processor.getClientConfigHazelcast().getCacheMap().get(key);
        logger.info("[{}] Data: {}", key, object);

        try {
            if(!(object == null || object.isEmpty())) {
                HazelcastProductEntity hazelcastProductEntity = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(object, HazelcastProductEntity.class);
                abstractService.save(hazelcastProductEntity);

                logger.info("[{}] Success inserted into the db", key);
            }
        } catch (Exception e) {
            logger.error("Error during deserialization for {}", object, e);
        }
    }
}
