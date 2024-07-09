package com.almod.flow.broker.activemq.util;

import com.almod.util.ObjectMapperSingleton;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCard;
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

    public Optional<ActiveMQPersonalCard> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getBody().toString();
        ActiveMQPersonalCard activeMQPersonalCard = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, ActiveMQPersonalCard.class);

        return service.save(activeMQPersonalCard);
    }
}
