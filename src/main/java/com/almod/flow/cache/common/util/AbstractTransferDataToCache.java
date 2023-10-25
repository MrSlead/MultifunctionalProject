package com.almod.flow.cache.common.util;

import com.almod.common.util.AbstractTransferData;
import com.almod.flow.cache.common.entity.CacheEntity;

public interface AbstractTransferDataToCache extends AbstractTransferData{
    void transferData(CacheEntity entity) throws Exception;
}
