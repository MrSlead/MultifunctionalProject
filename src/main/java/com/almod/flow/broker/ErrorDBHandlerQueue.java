package com.almod.flow.broker;

import com.almod.store.entity.ErrorDBEntity;
import com.almod.store.service.broker.ErrorDBService;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class ErrorDBHandlerQueue {
    private ErrorDBService errorDBService;

    @Autowired
    public void setErrorDBService(ErrorDBService errorDBService) {
        this.errorDBService = errorDBService;
    }

    public Optional<ErrorDBEntity> save(Exchange exchange) {
        ErrorDBEntity errorDBEntity = new ErrorDBEntity();
        Date date = new Date((Long) exchange.getIn().getHeader("JMSTimestamp"));

        errorDBEntity.builder()
                .dateTime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .errorText(exchange.getIn().getBody().toString().substring(0, 255))
                .errorDetail(exchange.getIn().getBody().toString())
                .UUID(exchange.getIn().getHeader("UUID").toString());

        return errorDBService.save(errorDBEntity);
    }
}
