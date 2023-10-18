package com.almod.flow.cache.hazelcast.processor;

import com.almod.flow.cache.hazelcast.controller.HazelcastController;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KeyWorker implements Runnable {

    private final Logger LOGGER = LoggerFactory.getLogger(HazelcastController.class);

    private final Processor processor;

    public KeyWorker(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        while(processor.isStarted()) {
            try {
                try {
                    work();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(processor.getKeyProcessingWait() > 0) {
                    Thread.sleep(processor.getKeyProcessingWait());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void work() {
        IMap<Object, Object> map = processor.getClientConfigHazelcast().getMap();
        for(Map.Entry<Object, Object> mapEntry : map.entrySet()) {
            FencedLock keyLock = (FencedLock) processor.getClientConfigHazelcast().getHazelcastInstance().getCPSubsystem().getLock(String.valueOf(mapEntry.getKey()));
            if(keyLock.tryLock()) {
                try {
                    keyProcessing(mapEntry.getKey());
                    map.remove(mapEntry.getKey());
                } finally {
                    if(keyLock.isLockedByCurrentThread()) {
                        keyLock.unlock();
                    }
                }
            }


            //processor.getClientConfigHazelcast().getHazelcastInstance().getMap("map").lock(mapEntry.getKey());
        }
    }

    private void keyProcessing(Object key) {
        LOGGER.info("Try get data from cache");

        String object = (String) processor.getClientConfigHazelcast().getMap().get(key);
        System.out.println(object);
    }
}
