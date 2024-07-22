package com.almod.flow.broker.activemq.infrastructure;

import com.almod.util.ObjectMapperSingleton;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import com.almod.store.service.broker.activemq.ActiveMQEmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveMQEmployeeHandlerQueue {
    private ActiveMQEmployeeService service;

    @Autowired
    public void setService(ActiveMQEmployeeService service) {
        this.service = service;
    }

    public Optional<ActiveMQEmployeeEntity> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getBody().toString();
        ActiveMQEmployeeEntity activeMQEmployeeEntity = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, ActiveMQEmployeeEntity.class);

        return service.save(activeMQEmployeeEntity);
    }
}
