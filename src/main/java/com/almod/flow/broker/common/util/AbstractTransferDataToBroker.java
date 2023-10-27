package com.almod.flow.broker.common.util;

import com.almod.common.util.AbstractTransferData;
import com.almod.flow.broker.common.entity.BrokerEntity;

public interface AbstractTransferDataToBroker extends AbstractTransferData {
    void transferData(BrokerEntity brokerEntity) throws Exception;
}
