package com.almod.store.service.broker.activemq;

import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCard;
import com.almod.store.repository.broker.activemq.ActiveMQPersonalCardRepo;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQPersonalCardServiceImpl extends AbstractServiceImpl<ActiveMQPersonalCard, Long> implements ActiveMQPersonalCardService {
    public ActiveMQPersonalCardServiceImpl(ActiveMQPersonalCardRepo repo) {
        super(repo);
    }
}
