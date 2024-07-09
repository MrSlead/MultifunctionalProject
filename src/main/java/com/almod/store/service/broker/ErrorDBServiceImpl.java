package com.almod.store.service.broker;

import com.almod.store.service.AbstractServiceImpl;
import com.almod.store.entity.ErrorDB;
import com.almod.store.repository.broker.ErrorDBRepo;
import org.springframework.stereotype.Service;

@Service
public class ErrorDBServiceImpl extends AbstractServiceImpl<ErrorDB, Long> implements ErrorDBService {
    public ErrorDBServiceImpl(ErrorDBRepo repo) {
        super(repo);
    }
}
