package com.almod.flow.cache.hazelcast.service;

import com.almod.common.service.AbstractServiceImpl;
import com.almod.flow.cache.hazelcast.entity.HazelcastProduct;
import com.almod.flow.cache.hazelcast.repo.HazelcastProductRepo;
import org.springframework.stereotype.Service;

@Service("HazelcastProductServiceImpl")
public class HazelcastProductServiceImpl extends AbstractServiceImpl<HazelcastProduct, Long> implements HazelcastProductService {
    public HazelcastProductServiceImpl(HazelcastProductRepo repo) {
        super(repo);
    }
}
