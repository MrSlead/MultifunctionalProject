package com.almod.store.repository.broker.activemq;

import com.almod.store.repository.AbstractRepo;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends AbstractRepo<EmployeeEntity, Long> {
}
