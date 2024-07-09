package com.almod.flow.cache.hazelcast.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWorker implements Worker {
    private final Logger LOGGER = LoggerFactory.getLogger(AbstractWorker.class);

    protected final Processor processor;

    public AbstractWorker(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        LOGGER.info("Starting Thread {}", Thread.currentThread().getName());
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
                if(!Thread.currentThread().isInterrupted()) Thread.currentThread().interrupt();
                LOGGER.error("Stopped Thread {}", Thread.currentThread().getName());
            }
        }
    }
}
