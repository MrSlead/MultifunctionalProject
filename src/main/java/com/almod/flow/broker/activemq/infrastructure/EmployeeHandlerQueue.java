package com.almod.flow.broker.activemq.infrastructure;

import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.util.ObjectMapperSingleton;
import com.almod.store.service.broker.activemq.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeHandlerQueue {
    private EmployeeService service;

    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }

    public Optional<EmployeeEntity> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getBody().toString();
        EmployeeEntity employeeEntity = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, EmployeeEntity.class);

        return service.save(employeeEntity);
    }
}
