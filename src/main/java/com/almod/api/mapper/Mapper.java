package com.almod.api.mapper;

import com.almod.api.annotation.TakeReturnType;

public interface Mapper<D, E> {
    @TakeReturnType
    D toDto(E e);

    E toEntity(D d);
}
