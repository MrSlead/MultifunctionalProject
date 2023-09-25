package com.almod.flow.broker.activemq.controller;

import com.almod.flow.broker.TransferDataToBroker;
import com.almod.flow.broker.activemq.ConstantsFlowBroker;
import com.almod.flow.broker.activemq.entity.ActiveMQPersonalCard;
import com.almod.flow.broker.activemq.entity.ServiceResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveMQController {

    private final Logger LOGGER = LoggerFactory.getLogger(ActiveMQController.class);

    private final TransferDataToBroker transferDataToBroker;

    public ActiveMQController(TransferDataToBroker transferDataToBroker) {
        this.transferDataToBroker = transferDataToBroker;
    }

    @PostMapping("/activemq")
    public ResponseEntity<ServiceResponse> upload(@Valid @RequestBody ActiveMQPersonalCard clientRequest) {
        LOGGER.info("Request received for ActiveMQ broker");
        ResponseEntity<ServiceResponse> response;

        try {
            LOGGER.info("Try to send a data to the queue " + ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE);
            transferDataToBroker.transferData(clientRequest, ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE);
        } catch (Exception e) {
            LOGGER.warn("Bad request");
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }
        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
