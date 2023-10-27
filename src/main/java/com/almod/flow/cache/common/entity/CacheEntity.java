package com.almod.flow.cache.common.entity;

import com.almod.common.entity.AbstractEntity;
import com.almod.flow.cache.type.hazelcast.entity.HazelcastProduct;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes(@JsonSubTypes.Type(value = HazelcastProduct.class))
public interface CacheEntity extends AbstractEntity {
}
