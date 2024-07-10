package com.almod.store.repository.broker.activemq;

import com.almod.store.repository.AbstractRepo;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCardEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveMQPersonalCardRepo extends AbstractRepo<ActiveMQPersonalCardEntity, Long> {
}
