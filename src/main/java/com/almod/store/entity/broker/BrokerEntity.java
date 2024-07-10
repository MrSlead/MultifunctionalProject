package com.almod.store.entity.broker;

import com.almod.store.entity.AbstractEntity;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCardEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes(@JsonSubTypes.Type(value = ActiveMQPersonalCardEntity.class))
public interface BrokerEntity extends AbstractEntity {
}
