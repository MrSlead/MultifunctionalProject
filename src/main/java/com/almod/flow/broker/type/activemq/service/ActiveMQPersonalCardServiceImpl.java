package com.almod.flow.broker.type.activemq.service;

import com.almod.common.service.AbstractServiceImpl;
import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import com.almod.flow.broker.type.activemq.repo.ActiveMQPersonalCardRepo;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQPersonalCardServiceImpl extends AbstractServiceImpl<ActiveMQPersonalCard, Long> implements ActiveMQPersonalCardService {
    public ActiveMQPersonalCardServiceImpl(ActiveMQPersonalCardRepo repo) {
        super(repo);
    }
}
