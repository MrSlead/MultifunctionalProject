package com.almod.store.repository.cache.hazelcast;

import com.almod.store.repository.AbstractRepo;
import com.almod.store.entity.cache.hazelcast.HazelcastProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface HazelcastProductRepo extends AbstractRepo<HazelcastProduct, Long> {
}
