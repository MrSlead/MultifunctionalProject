package com.almod.flow.broker.type.activemq.controller;

import com.almod.flow.broker.common.util.AbstractTransferDataToBroker;
import com.almod.flow.broker.type.activemq.ConstantsFlowBroker;
import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import com.almod.common.entity.ServiceResponse;
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
public class ActiveMQController {

    private final Logger LOGGER = LoggerFactory.getLogger(ActiveMQController.class);

    @Qualifier("TransferDataToActiveMQ")
    private AbstractTransferDataToBroker abstractTransferDataToBroker;

    @Autowired
    public void setAbstractTransferDataToBroker(AbstractTransferDataToBroker abstractTransferDataToBroker) {
        this.abstractTransferDataToBroker = abstractTransferDataToBroker;
    }

    @PostMapping("/activemq")
    public ResponseEntity<ServiceResponse> upload(@Valid @RequestBody ActiveMQPersonalCard clientRequest) {
        LOGGER.info(String.format("[%s] Request received for ActiveMQ broker", clientRequest.getUUID()));

        ResponseEntity<ServiceResponse> response;

        try {
            LOGGER.info(String.format("[%s] Try to send a data to the queue %s", clientRequest.getUUID(), ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE));
            abstractTransferDataToBroker.transferData(clientRequest, ConstantsFlowBroker.ACTIVEMQ_FLOW_PERSONAL_CARD_QUEUE);
        } catch (Exception e) {
            LOGGER.warn(String.format("[%s] Bad request", clientRequest.getUUID()));
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }
        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }
}
