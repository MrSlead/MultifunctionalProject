package com.almod.api.mapper;

import com.almod.api.dto.cache.hazelcast.ProductDto;
import com.almod.store.entity.cache.hazelcast.HazelcastProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(HazelcastProductEntity entity);
    HazelcastProductEntity toEntity(ProductDto dto);
}
