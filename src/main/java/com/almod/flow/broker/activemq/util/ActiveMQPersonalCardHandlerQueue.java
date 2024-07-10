package com.almod.flow.broker.activemq.util;

import com.almod.util.ObjectMapperSingleton;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCardEntity;
import com.almod.store.service.broker.activemq.ActiveMQPersonalCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveMQPersonalCardHandlerQueue {
    private ActiveMQPersonalCardService service;

    @Autowired
    public void setService(ActiveMQPersonalCardService service) {
        this.service = service;
    }

    public Optional<ActiveMQPersonalCardEntity> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getBody().toString();
        ActiveMQPersonalCardEntity activeMQPersonalCardEntity = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, ActiveMQPersonalCardEntity.class);

        return service.save(activeMQPersonalCardEntity);
    }
}
