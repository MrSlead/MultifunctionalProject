package com.almod.flow.cache.hazelcast.service;

import com.almod.common.repo.AbstractRepo;
import com.almod.common.service.AbstractServiceImpl;
import com.almod.flow.cache.hazelcast.entity.HazelcastProduct;
import com.almod.flow.cache.hazelcast.repo.HazelcastProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastProductServiceImpl extends AbstractServiceImpl<HazelcastProduct, Long> implements HazelcastProductService {
    public HazelcastProductServiceImpl(HazelcastProductRepo repo) {
        super(repo);
    }
}
