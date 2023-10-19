package com.almod.flow.cache.common.util;

import com.almod.common.util.AbstractTransferData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AbstractTransferDataToCache<T> extends AbstractTransferData{
    void transferData(T t) throws JsonProcessingException;
}
