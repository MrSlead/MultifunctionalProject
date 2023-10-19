package com.almod.common.service;

import com.almod.common.repo.AbstractRepo;

import java.util.List;
import java.util.Optional;

public class AbstractServiceImpl<T, ID> implements AbstractService<T, ID> {
    private AbstractRepo<T, ID> repo;

    public AbstractServiceImpl(AbstractRepo<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        if(id == null) return Optional.empty();

        return repo.findById(id);
    }

    @Override
    public Optional<T> findByUUID(String UUID) {
        if(UUID == null || UUID.isEmpty()) return Optional.empty();

        return repo.findByUUID(UUID);
    }

    @Override
    public Optional<T> save(T t) {
        if(t != null)
            return Optional.of(repo.save(t));
        else return Optional.of(t);
    }
}
