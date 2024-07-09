package com.almod.flow.cache.hazelcast.worker;

import com.almod.store.service.AbstractService;
import com.almod.config.ClientConfigHazelcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class Processor implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(Processor.class);

    private Thread thisThread;
    private volatile boolean started;

    @Value("${key.processing.wait}")
    private volatile int keyProcessingWait;

    @Value("${number.thread}")
    private int parallelism;

    private ClientConfigHazelcast clientConfigHazelcast;

    private AbstractService abstractService;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @Autowired
    public void setAbstractService(@Qualifier("HazelcastProductServiceImpl") AbstractService abstractService) {
        this.abstractService = abstractService;
    }

    @PostConstruct
    public void start() {
        if(isStarted()) {
            logger.info("Already running");
            return;
        }

        thisThread = new Thread(this, "Processor");
        thisThread.setDaemon(false);
        thisThread.start();
        setStarted(true);

        logger.info("Processor has started");
    }

    @PreDestroy
    public void stop() {
        if(!isStarted()) {
            logger.info("Processor not running");
            return;
        }
        setStarted(false);
        thisThread.interrupt();

        try {
            thisThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("Processor has stopped");
    }

    @Override
    public void run() {
        ExecutorService executorService = null;
        List<Future> tasks = new ArrayList<>();
        try {
            logger.info("Number of threads = {}", parallelism);
            executorService = Executors.newFixedThreadPool(parallelism);
            
            for(int i = 0; i < parallelism; i++) {
                Future<?> task = executorService.submit(new MapWorker(this, abstractService));
                tasks.add(task);
            }

            TimeUnit.DAYS.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if(!Thread.currentThread().isInterrupted()) Thread.currentThread().interrupt();
            executorService.shutdown();

            for(Future task : tasks) {
                if(!task.isDone()) task.cancel(true);
            }
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getKeyProcessingWait() {
        return keyProcessingWait;
    }

    public void setKeyProcessingWait(int keyProcessingWait) {
        this.keyProcessingWait = keyProcessingWait;
    }

    public int getParallelism() {
        return parallelism;
    }

    public void setParallelism(int parallelism) {
        this.parallelism = parallelism;
    }

    public ClientConfigHazelcast getClientConfigHazelcast() {
        return clientConfigHazelcast;
    }
}
