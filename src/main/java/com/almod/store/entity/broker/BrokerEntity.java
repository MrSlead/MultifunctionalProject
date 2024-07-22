package com.almod.store.entity.broker;

import com.almod.store.entity.AbstractEntity;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeEntity.class),
        @JsonSubTypes.Type(value = VacationEntity.class)
})
public interface BrokerEntity extends AbstractEntity {
}
