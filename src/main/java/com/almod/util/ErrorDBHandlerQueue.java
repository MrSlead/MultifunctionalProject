package com.almod.util;

import com.almod.flow.broker.entity.ErrorDB;
import com.almod.flow.broker.service.ErrorDBService;
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

    public Optional<ErrorDB> save(Exchange exchange) {
        ErrorDB errorDB = new ErrorDB();
        Date date = new Date((Long) exchange.getIn().getHeader("JMSTimestamp"));

        errorDB.setDateTime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        errorDB.setErrorText(exchange.getIn().getBody().toString().substring(0, 255));
        errorDB.setErrorDetail(exchange.getIn().getBody().toString());
        errorDB.setUUID(exchange.getIn().getHeader("UUID").toString());

        return errorDBService.save(errorDB);
    }
}
