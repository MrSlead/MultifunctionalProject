package com.almod.store.service.broker.activemq;

import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.store.repository.broker.activemq.VacationRepo;
import com.almod.store.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VacationServiceImpl extends AbstractServiceImpl<VacationEntity, Long> implements VacationService {
    public VacationServiceImpl(VacationRepo repo) {
        super(repo);
    }
}
