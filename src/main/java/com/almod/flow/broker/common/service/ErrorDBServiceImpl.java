package com.almod.flow.broker.common.service;

import com.almod.common.service.AbstractServiceImpl;
import com.almod.flow.broker.common.entity.ErrorDB;
import com.almod.flow.broker.common.repo.ErrorDBRepo;
import org.springframework.stereotype.Service;

@Service
public class ErrorDBServiceImpl extends AbstractServiceImpl<ErrorDB, Long> implements ErrorDBService {
    public ErrorDBServiceImpl(ErrorDBRepo repo) {
        super(repo);
    }
}
