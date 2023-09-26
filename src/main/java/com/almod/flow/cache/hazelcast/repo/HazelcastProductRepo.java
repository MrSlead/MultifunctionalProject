package com.almod.flow.cache.hazelcast.repo;

import com.almod.common.repo.AbstractRepo;
import com.almod.flow.cache.hazelcast.entity.HazelcastProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface HazelcastProductRepo extends AbstractRepo<HazelcastProduct, Long> {
}
