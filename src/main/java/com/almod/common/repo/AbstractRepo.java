package com.almod.common.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepo<T, ID> extends CrudRepository<T, ID> {
    List<T> findAll();
    Optional<T> findByUUID(String UUID);
}
