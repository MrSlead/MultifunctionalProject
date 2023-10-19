package com.almod.flow.cache.hazelcast.multithreading;

import com.almod.flow.cache.hazelcast.controller.HazelcastController;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class MapWorker extends AbstractWorker {

    private final Logger LOGGER = LoggerFactory.getLogger(HazelcastController.class);

    public MapWorker(Processor processor) {
        super(processor);
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
                        }
                    } catch (Exception e) {
                        LOGGER.error("Error unlock key = {}", mapEntry.getKey());
                    }
                }
            }
        }
    }

    private void keyProcessing(Object key) {
        LOGGER.info("Try get data from cache");

        String object = (String) processor.getClientConfigHazelcast().getMap().get(key);
        LOGGER.info("Data: {}", object);
    }
}
