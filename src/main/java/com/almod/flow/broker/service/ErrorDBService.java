package com.almod.flow.broker.service;

import com.almod.flow.broker.entity.ErrorDB;
import com.almod.flow.broker.repo.ErrorDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErrorDBService implements AbstractService<ErrorDB, Long> {
    private ErrorDBRepo errorDBRepo;

    @Autowired
    public void setErrorRepo(ErrorDBRepo errorDBRepo) {
        this.errorDBRepo = errorDBRepo;
    }

    @Override
    public List<ErrorDB> findAll() {
        return null;
    }

    @Override
    public Optional<ErrorDB> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<ErrorDB> save(ErrorDB errorDB) {
        return Optional.of(errorDBRepo.save(errorDB));
    }

    @Override
    public Optional<ErrorDB> update(ErrorDB errorDB) {
        return Optional.empty();
    }

    @Override
    public void delete(ErrorDB errorDB) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
