package com.almod.flow.broker.repo;

import com.almod.flow.broker.entity.ErrorDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorDBRepo extends CrudRepository<ErrorDB, Long> {
    List<ErrorDB> findAll();
}
