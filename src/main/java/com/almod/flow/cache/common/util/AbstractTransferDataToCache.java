package com.almod.flow.cache.common.util;

import com.almod.common.util.AbstractTransferData;

public interface AbstractTransferDataToCache<T> extends AbstractTransferData{
    void transferData(T t);
}
