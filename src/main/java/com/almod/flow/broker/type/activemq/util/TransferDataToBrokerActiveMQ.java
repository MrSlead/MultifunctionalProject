package com.almod.flow.broker.type.activemq.util;

import com.almod.flow.broker.common.entity.BrokerEntity;
import com.almod.flow.broker.common.util.TransferDataToBroker;
import com.almod.flow.broker.type.activemq.ConstantsFlowBroker;
import com.almod.flow.broker.type.activemq.ObjectMapperSingleton;
import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToBrokerActiveMQ extends TransferDataToBroker {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToBrokerActiveMQ.class);

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void transferData(BrokerEntity brokerEntity) throws Exception {
        String clientRequestString = ObjectMapperSingleton.getCustomizedObjectMapper().writeValueAsString(brokerEntity);

        if(brokerEntity instanceof ActiveMQPersonalCard) {
            String queue = ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE;
            LOGGER.info(String.format("[%s] Try to send a data to the queue %s", brokerEntity.getUUID(), queue));
            jmsTemplate.send(queue, session -> {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(clientRequestString);
                textMessage.setStringProperty("UUID", brokerEntity.getUUID());
                //textMessage.setStringProperty("clientRequestString", clientRequestString);

                return textMessage;
            });
            LOGGER.info(String.format("[%s] The data has been successfully inserted into the queue: %s", brokerEntity.getUUID(), queue));
        } else throw new Exception("Unknown entity");
    }
}
