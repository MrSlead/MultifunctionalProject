package com.almod.flow.broker.activemq;

import com.almod.flow.broker.TransferDataToBroker;
import com.almod.flow.broker.ConstantsFlowBroker;
import com.almod.store.entity.broker.BrokerEntity;
import com.almod.util.ObjectMapperSingleton;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("TransferDataToBrokerActiveMQ")
public class TransferDataToBrokerActiveMQ extends TransferDataToBroker {
    private final Logger logger = LoggerFactory.getLogger(TransferDataToBrokerActiveMQ.class);

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void transferData(BrokerEntity activemqEntity) {
        try {
            String clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(activemqEntity);

            String queue = ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE;
            logger.info("[{}] Try to send a data to the queue {}", activemqEntity.getUUID(), queue);
            jmsTemplate.send(queue, session -> {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(clientRequestString);
                textMessage.setStringProperty("UUID", activemqEntity.getUUID());

                return textMessage;
            });
            logger.info("[{}] The data has been successfully inserted into the queue: {}", activemqEntity.getUUID(), queue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
