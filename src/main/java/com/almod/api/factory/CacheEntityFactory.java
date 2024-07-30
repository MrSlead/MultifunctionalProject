package com.almod.api.factory;

import com.almod.api.dto.cache.CacheDto;
import com.almod.store.entity.cache.CacheEntity;
import org.springframework.stereotype.Component;

@Component("CacheEntityFactory")
public class CacheEntityFactory extends EntityFactory<CacheDto, CacheEntity> {
}
