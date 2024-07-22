package com.almod.store.repository.broker.activemq;

import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.store.repository.AbstractRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepo extends AbstractRepo<VacationEntity, Long> {
}
