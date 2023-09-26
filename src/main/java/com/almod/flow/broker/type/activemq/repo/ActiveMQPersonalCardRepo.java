package com.almod.flow.broker.type.activemq.repo;

import com.almod.common.repo.AbstractRepo;
import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveMQPersonalCardRepo extends AbstractRepo<ActiveMQPersonalCard, Long> {
}
