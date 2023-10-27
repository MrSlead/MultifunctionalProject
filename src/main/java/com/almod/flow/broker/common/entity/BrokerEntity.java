package com.almod.flow.broker.common.entity;

import com.almod.common.entity.AbstractEntity;
import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes(@JsonSubTypes.Type(value = ActiveMQPersonalCard.class))
public interface BrokerEntity extends AbstractEntity {
}
