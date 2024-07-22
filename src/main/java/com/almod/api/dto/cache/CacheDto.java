package com.almod.api.dto.cache;

import com.almod.api.dto.cache.hazelcast.ProductDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductDto.class)
})
public interface CacheDto {
}
