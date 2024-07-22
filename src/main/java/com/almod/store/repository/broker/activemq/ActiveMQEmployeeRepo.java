package com.almod.store.repository.broker.activemq;

import com.almod.store.repository.AbstractRepo;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveMQEmployeeRepo extends AbstractRepo<ActiveMQEmployeeEntity, Long> {
}
