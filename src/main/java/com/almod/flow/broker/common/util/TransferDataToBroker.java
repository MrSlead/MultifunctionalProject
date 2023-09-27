package com.almod.flow.broker.common.util;

import com.almod.flow.broker.type.activemq.ObjectMapperSingleton;
import com.almod.common.entity.AbstractEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToBroker implements AbstractTransferDataToBroker<AbstractEntity, String> {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToBroker.class);

    private JmsTemplate jmsTemplate;

    public TransferDataToBroker(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void transferData(AbstractEntity clientRequest, String queue) throws JsonProcessingException {
        String clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(clientRequest);

        jmsTemplate.send(queue, session -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(clientRequestString);
            textMessage.setStringProperty("UUID", clientRequest.getUUID());
            //textMessage.setStringProperty("clientRequestString", clientRequestString);

            return textMessage;
        });

        LOGGER.info(String.format("[%s] The data has been successfully inserted into the queue: %s", clientRequest.getUUID(), queue));
    }
}
