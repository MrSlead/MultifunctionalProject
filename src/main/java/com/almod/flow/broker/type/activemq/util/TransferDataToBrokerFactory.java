package com.almod.flow.broker.type.activemq.util;

import com.almod.flow.broker.common.entity.ActiveMQEntity;
import com.almod.flow.broker.common.entity.BrokerEntity;
import com.almod.flow.broker.common.util.TransferDataToBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TransferDataToBrokerFactory {
    private TransferDataToBrokerActiveMQ transferDataToBrokerActiveMQ;

    @Lazy
    @Autowired
    public void setTransferDataToBrokerActiveMQ(TransferDataToBrokerActiveMQ transferDataToBrokerActiveMQ) {
        this.transferDataToBrokerActiveMQ = transferDataToBrokerActiveMQ;
    }

    public TransferDataToBroker getDependenceForTransferData(BrokerEntity entity) throws Exception {
        if(entity instanceof ActiveMQEntity) {
            if(transferDataToBrokerActiveMQ == null) transferDataToBrokerActiveMQ = new TransferDataToBrokerActiveMQ();
            return transferDataToBrokerActiveMQ;
        }
        else throw new Exception();
    }
}
