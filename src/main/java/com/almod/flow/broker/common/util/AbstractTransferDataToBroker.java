package com.almod.flow.broker.common.util;

import com.almod.common.util.AbstractTransferData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AbstractTransferDataToBroker<T, E> extends AbstractTransferData {
    void transferData(T t, E e) throws JsonProcessingException;
}
