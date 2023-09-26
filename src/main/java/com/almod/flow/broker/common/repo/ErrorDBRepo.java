package com.almod.flow.broker.common.repo;

import com.almod.common.repo.AbstractRepo;
import com.almod.flow.broker.common.entity.ErrorDB;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorDBRepo extends AbstractRepo<ErrorDB, Long> {
}
