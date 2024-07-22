package com.almod.api.factory;

import com.almod.api.dto.cache.CacheDto;
import com.almod.api.dto.cache.hazelcast.ProductDto;
import com.almod.api.mapper.ProductMapper;
import com.almod.store.entity.cache.CacheEntity;
import com.almod.store.entity.cache.hazelcast.HazelcastProductEntity;
import com.almod.util.SpringApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("CacheEntityFactory")
public class CacheEntityFactory implements EntityFactory<CacheDto, CacheEntity> {
    @Override
    public Optional<CacheEntity> getEntity(CacheDto dto) {
        try {
            if(dto instanceof ProductDto) {
                var instance = (ProductMapper) SpringApplicationContext.getContext().getBean("productMapperImpl");

                HazelcastProductEntity product = instance.toEntity((ProductDto) dto);

                return Optional.of(product);
            }
            else
                throw new Exception("Unknown dto type: " + dto.getClass().getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
