package com.almod.flow.broker;

import com.almod.flow.broker.activemq.ObjectMapperSingleton;
import com.almod.flow.broker.entity.AbstractClientRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToBroker {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToBroker.class);

    private JmsTemplate jmsTemplate;

    public TransferDataToBroker(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void transferData(AbstractClientRequest clientRequest, String queue) throws JsonProcessingException {
        String clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(clientRequest);

        jmsTemplate.send(queue, session -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setStringProperty("clientRequestString", clientRequestString);

            return textMessage;
        });

        LOGGER.info("The data has been successfully inserted into the queue: " + queue);
    }
}
