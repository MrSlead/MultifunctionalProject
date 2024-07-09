package com.almod.store.entity.broker;

import com.almod.store.entity.AbstractEntity;
import com.almod.store.entity.broker.activemq.ActiveMQPersonalCard;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes(@JsonSubTypes.Type(value = ActiveMQPersonalCard.class))
public interface BrokerEntity extends AbstractEntity {
}
