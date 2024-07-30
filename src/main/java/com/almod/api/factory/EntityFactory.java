package com.almod.api.factory;

import com.almod.api.mapper.Mapper;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class EntityFactory<D, E> {
    @Getter
    private static Map<Class, Mapper> mapperMap = new HashMap<>();

    public Optional<E> getEntity(D dto) {
        Mapper mapper = mapperMap.get(dto.getClass());
        E entity = (E) mapper.toEntity(dto);

        return Optional.of(entity);
    }
}
