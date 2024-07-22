package com.almod.store.service.broker.activemq;

import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import com.almod.store.repository.broker.activemq.ActiveMQEmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQEmployeeServiceImpl extends AbstractServiceImpl<ActiveMQEmployeeEntity, Long> implements ActiveMQEmployeeService {
    public ActiveMQEmployeeServiceImpl(ActiveMQEmployeeRepo repo) {
        super(repo);
    }
}
