package com.almod.flow.broker.activemq.repo;

import com.almod.flow.broker.activemq.entity.ActiveMQClientRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveMQClientRequestRepo extends CrudRepository<ActiveMQClientRequest, Long> {
    List<ActiveMQClientRequest> findAll();
}
