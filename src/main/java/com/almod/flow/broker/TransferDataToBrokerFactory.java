package com.almod.flow.broker;

import com.almod.flow.AbstractTransferDataFactory;
import com.almod.store.entity.broker.BrokerEntity;
import com.almod.store.entity.broker.activemq.ActiveMQEntity;
import com.almod.util.SpringApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToBrokerFactory implements AbstractTransferDataFactory<BrokerEntity> {
    @Override
    public TransferDataToBroker getDependenceForTransferData(BrokerEntity entity) {
        try {
            TransferDataToBroker instance;
            if(entity instanceof ActiveMQEntity) {
                instance = (TransferDataToBroker) SpringApplicationContext.getContext().getBean("TransferDataToBrokerActiveMQ");

                return instance;
            }
            else
                throw new Exception("Unknown entity type: " + entity.getClass().getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
