package com.almod.store.service.broker.activemq;

import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCardEntity;
import com.almod.store.repository.broker.activemq.ActiveMQPersonalCardRepo;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQPersonalCardServiceImpl extends AbstractServiceImpl<ActiveMQPersonalCardEntity, Long> implements ActiveMQPersonalCardService {
    public ActiveMQPersonalCardServiceImpl(ActiveMQPersonalCardRepo repo) {
        super(repo);
    }
}
