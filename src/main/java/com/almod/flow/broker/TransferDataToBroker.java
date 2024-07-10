package com.almod.flow.broker;

import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.broker.BrokerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransferDataToBroker")
public class TransferDataToBroker implements AbstractTransferData<BrokerEntity> {
    private final Logger logger = LoggerFactory.getLogger(TransferDataToBroker.class);

    private TransferDataToBrokerFactory transferDataToBrokerFactory;

    @Autowired
    public void setTransferDataToBrokerFactory(TransferDataToBrokerFactory transferDataToBrokerFactory) {
        this.transferDataToBrokerFactory = transferDataToBrokerFactory;
    }

    @Override
    public void transferData(BrokerEntity brokerEntity) {
        TransferDataToBroker instance = transferDataToBrokerFactory.getDependenceForTransferData(brokerEntity);
        instance.transferData(brokerEntity);
    }

}
