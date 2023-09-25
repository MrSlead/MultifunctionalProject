package com.almod.flow.broker.activemq.service;

import com.almod.flow.broker.AbstractService;
import com.almod.flow.broker.activemq.entity.ActiveMQPersonalCard;
import com.almod.flow.broker.activemq.repo.ActiveMQPersonalCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiveMQPersonalCardService implements AbstractService<ActiveMQPersonalCard, Long> {
    private ActiveMQPersonalCardRepo repo;

    @Autowired
    public void setRepo(ActiveMQPersonalCardRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<ActiveMQPersonalCard> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ActiveMQPersonalCard> findById(Long aLong) {
        if(aLong == null || aLong < 0) return Optional.empty();

        return repo.findById(aLong);
    }

    @Override
    public Optional<ActiveMQPersonalCard> save(ActiveMQPersonalCard request) {
        return Optional.of(repo.save(request));
    }

    @Override
    public Optional<ActiveMQPersonalCard> update(ActiveMQPersonalCard request) {
        return Optional.of(repo.save(request));
    }

    @Override
    public void delete(ActiveMQPersonalCard request) {
        repo.delete(request);
    }

    @Override
    public void deleteById(Long aLong) {
        if(aLong == null || aLong < 0) return;
        if(!repo.findById(aLong).isPresent()) return;

        repo.deleteById(aLong);
    }
}
