package com.almod.store.service.broker.activemq;

import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.store.repository.broker.activemq.EmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractServiceImpl<EmployeeEntity, Long> implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepo repo) {
        super(repo);
    }
}
