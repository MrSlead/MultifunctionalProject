package com.almod.store.entity.cache;

import com.almod.store.entity.AbstractEntity;
import com.almod.store.entity.cache.hazelcast.HazelcastProduct;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes(@JsonSubTypes.Type(value = HazelcastProduct.class))
public interface CacheEntity extends AbstractEntity {
}
