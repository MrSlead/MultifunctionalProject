package com.almod.store.service.cache.hazelcast;

import com.almod.store.repository.cache.hazelcast.HazelcastProductRepo;
import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.cache.hazelcast.HazelcastProductEntity;
import org.springframework.stereotype.Service;

@Service("HazelcastProductServiceImpl")
public class HazelcastProductServiceImpl extends AbstractServiceImpl<HazelcastProductEntity, Long> implements HazelcastProductService {
    public HazelcastProductServiceImpl(HazelcastProductRepo repo) {
        super(repo);
    }
}
