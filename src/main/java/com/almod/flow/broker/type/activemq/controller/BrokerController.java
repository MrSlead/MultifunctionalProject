package com.almod.flow.broker.type.activemq.controller;

import com.almod.common.entity.ServiceResponse;
import com.almod.flow.broker.common.entity.BrokerEntity;
import com.almod.flow.broker.common.util.AbstractTransferDataToBroker;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokerController {

    private final Logger LOGGER = LoggerFactory.getLogger(BrokerController.class);

    private AbstractTransferDataToBroker abstractTransferDataToBroker;

    @Autowired
    public void setAbstractTransferDataToBroker(@Qualifier("TransferDataToBroker") AbstractTransferDataToBroker abstractTransferDataToBroker) {
        this.abstractTransferDataToBroker = abstractTransferDataToBroker;
    }

    @PostMapping("/broker")
    public ResponseEntity<ServiceResponse> upload(@Valid @RequestBody BrokerEntity clientRequest) {
        LOGGER.info(String.format("[%s] Request received for broker", clientRequest.getUUID()));

        ResponseEntity<ServiceResponse> response;

        try {
            abstractTransferDataToBroker.transferData(clientRequest);
        } catch (Exception e) {
            LOGGER.warn(String.format("[%s] Bad request", clientRequest.getUUID()));
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }
        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }
}
