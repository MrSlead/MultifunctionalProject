package com.almod.flow.cache.hazelcast.multithreading;

public interface Worker extends Runnable {
    void work();
}
