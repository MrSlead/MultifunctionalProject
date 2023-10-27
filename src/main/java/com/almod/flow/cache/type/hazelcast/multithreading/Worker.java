package com.almod.flow.cache.type.hazelcast.multithreading;

public interface Worker extends Runnable {
    void work();
}
