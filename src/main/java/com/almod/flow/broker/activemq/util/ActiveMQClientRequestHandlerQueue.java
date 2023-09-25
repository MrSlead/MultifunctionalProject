package com.almod.flow.broker.activemq.util;

import com.almod.flow.broker.activemq.ObjectMapperSingleton;
import com.almod.flow.broker.activemq.entity.ActiveMQClientRequest;
import com.almod.flow.broker.activemq.service.ActiveMQClientRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ActiveMQClientRequestHandlerQueue {
    private ActiveMQClientRequestService service;

    @Autowired
    public void setService(ActiveMQClientRequestService service) {
        this.service = service;
    }

    public Optional<ActiveMQClientRequest> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getHeader("clientRequestString", String.class);
        ActiveMQClientRequest activeMQClientRequest = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, ActiveMQClientRequest.class);

        return service.save(activeMQClientRequest);
    }
}
