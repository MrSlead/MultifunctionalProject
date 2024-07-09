package com.almod.store.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    Optional<T> findByUUID(String UUID);
    Optional<T> save(T t);
}
