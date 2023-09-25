package com.almod.flow.broker.activemq.service;

import com.almod.flow.broker.AbstractService;
import com.almod.flow.broker.activemq.entity.ActiveMQClientRequest;
import com.almod.flow.broker.activemq.repo.ActiveMQClientRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiveMQClientRequestService implements AbstractService<ActiveMQClientRequest, Long> {
    private ActiveMQClientRequestRepo repo;

    @Autowired
    public void setRepo(ActiveMQClientRequestRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<ActiveMQClientRequest> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ActiveMQClientRequest> findById(Long aLong) {
        if(aLong == null || aLong < 0) return Optional.empty();

        return repo.findById(aLong);
    }

    @Override
    public Optional<ActiveMQClientRequest> save(ActiveMQClientRequest request) {
        return Optional.of(repo.save(request));
    }

    @Override
    public Optional<ActiveMQClientRequest> update(ActiveMQClientRequest request) {
        return Optional.of(repo.save(request));
    }

    @Override
    public void delete(ActiveMQClientRequest request) {
        repo.delete(request);
    }

    @Override
    public void deleteById(Long aLong) {
        if(aLong == null || aLong < 0) return;
        if(!repo.findById(aLong).isPresent()) return;

        repo.deleteById(aLong);
    }
}
