package com.almod.api.controller;

import com.almod.api.ServiceResponse;
import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.broker.BrokerEntity;
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

    private AbstractTransferData abstractTransferData;

    @Autowired
    public void setAbstractTransferDataToBroker(@Qualifier("TransferDataToBroker") AbstractTransferData abstractTransferData) {
        this.abstractTransferData = abstractTransferData;
    }

    @PostMapping("/broker")
    public ResponseEntity<ServiceResponse> upload(@Valid @RequestBody BrokerEntity clientRequest) {
        LOGGER.info(String.format("[%s] Request received for broker", clientRequest.getUUID()));

        ResponseEntity<ServiceResponse> response;

        try {
            abstractTransferData.transferData(clientRequest);
        } catch (Exception e) {
            LOGGER.warn(String.format("[%s] Bad request: {}", clientRequest.getUUID()), e);
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }
        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }
}
