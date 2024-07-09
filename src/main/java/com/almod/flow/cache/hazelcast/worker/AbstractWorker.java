package com.almod.flow.cache.hazelcast.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWorker implements Worker {
    private final Logger logger = LoggerFactory.getLogger(AbstractWorker.class);

    protected final Processor processor;

    public AbstractWorker(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        logger.info("Starting Thread {}", Thread.currentThread().getName());
        while (!Thread.currentThread().isInterrupted() && processor.isStarted()) {
            try {
                try {
                    work();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                if (processor.getKeyProcessingWait() > 0) {
                    Thread.sleep(processor.getKeyProcessingWait());
                }
            } catch (InterruptedException e) {
                if (!Thread.currentThread().isInterrupted()) Thread.currentThread().interrupt();
                logger.error("Stopped Thread {}", Thread.currentThread().getName());
            }
        }
    }
}
