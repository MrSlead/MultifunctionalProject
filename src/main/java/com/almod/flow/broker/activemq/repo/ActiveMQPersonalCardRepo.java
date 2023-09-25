package com.almod.flow.broker.activemq.repo;

import com.almod.flow.broker.activemq.entity.ActiveMQPersonalCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveMQPersonalCardRepo extends CrudRepository<ActiveMQPersonalCard, Long> {
    List<ActiveMQPersonalCard> findAll();
}
