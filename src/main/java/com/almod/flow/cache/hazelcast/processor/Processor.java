package com.almod.flow.cache.hazelcast.processor;

import com.almod.flow.cache.hazelcast.config.ClientConfigHazelcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private Thread thisThread;
    private volatile boolean started;
    private volatile int keyProcessingWait = 500;
    private int parallelism = 10;

    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }


    @PostConstruct
    public void start() {
        if(isStarted()) {
            LOGGER.info("Already running");
            return;
        }

        thisThread = new Thread(this, "Processor");
        thisThread.setDaemon(false);
        thisThread.start();
        setStarted(true);

        LOGGER.info("Processor has started");
    }

    @PreDestroy
    public void stop() {
        if(!isStarted()) {
            LOGGER.info("Not running");
            return;
        }
        setStarted(false);
        thisThread.interrupt();

        try {
            thisThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Processor has stopped");
    }

    @Override
    public void run() {
        ExecutorService executorService = null;
        List<Future> tasks = new ArrayList<>();
        try {
            LOGGER.info("Processor starting, number of threads = {}", parallelism);
            executorService = Executors.newFixedThreadPool(parallelism);
            
            for(int i = 0; i < parallelism; i++) {
                Future<?> task = executorService.submit(new KeyWorker(this));
                tasks.add(task);
            }

            TimeUnit.DAYS.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            
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
