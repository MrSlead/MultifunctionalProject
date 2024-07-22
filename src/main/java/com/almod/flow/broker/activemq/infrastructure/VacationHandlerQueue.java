package com.almod.flow.broker.activemq.infrastructure;

import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.store.service.broker.activemq.VacationService;
import com.almod.util.ObjectMapperSingleton;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VacationHandlerQueue {
    private VacationService service;

    @Autowired
    public void setService(VacationService service) {
        this.service = service;
    }

    public Optional<VacationEntity> save(Exchange exchange) throws JsonProcessingException {
        Message message = exchange.getIn();
        String clientRequestString = message.getBody().toString();
        VacationEntity vacationEntity = ObjectMapperSingleton.getCustomizedObjectMapper().readValue(clientRequestString, VacationEntity.class);
        vacationEntity.getActiveMQEmployeeEntity().getVacationEntityList().add(vacationEntity);

        return service.save(vacationEntity);
    }
}
