package com.almod.flow.broker.common.util;

import com.almod.flow.broker.common.entity.ActiveMQEntity;
import com.almod.flow.broker.common.entity.BrokerEntity;
import com.almod.flow.broker.type.activemq.util.TransferDataToBrokerActiveMQ;
import com.almod.flow.broker.type.activemq.util.TransferDataToBrokerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToBroker")
public class TransferDataToBroker implements AbstractTransferDataToBroker {
    private final Logger LOGGER = LoggerFactory.getLogger(TransferDataToBroker.class);

    private TransferDataToBrokerFactory transferDataToBrokerFactory;

    @Autowired
    public void setTransferDataToBrokerFactory(TransferDataToBrokerFactory transferDataToBrokerFactory) {
        this.transferDataToBrokerFactory = transferDataToBrokerFactory;
    }

    public void transferData(BrokerEntity brokerEntity) throws Exception {
        if(brokerEntity instanceof ActiveMQEntity) {
            TransferDataToBrokerActiveMQ instance = (TransferDataToBrokerActiveMQ) transferDataToBrokerFactory.getDependenceForTransferData(brokerEntity);
            instance.transferData(brokerEntity);
        } else throw new Exception("Unknown entity");
    }
}
