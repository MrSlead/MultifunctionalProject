package com.almod.api.controller;

import com.almod.api.dto.broker.BrokerDto;
import com.almod.api.factory.EntityFactory;
import com.almod.api.util.ResponseUtils;
import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.broker.BrokerEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name="Broker's flow", description="The flow for uploading data to the broker")
public class BrokerController {
    private final Logger logger = LoggerFactory.getLogger(BrokerController.class);

    private AbstractTransferData abstractTransferData;
    private EntityFactory brokerEntityFactory;

    @Autowired
    public void setAbstractTransferDataToBroker(@Qualifier("TransferDataToBroker") AbstractTransferData abstractTransferData) {
        this.abstractTransferData = abstractTransferData;
    }

    @Autowired
    public void setBrokerEntityFactory(@Qualifier("BrokerEntityFactory") EntityFactory brokerEntityFactory) {
        this.brokerEntityFactory = brokerEntityFactory;
    }

    public static final String BROKER_FLOW = "/api/flow/broker/upload";

    @PostMapping(BROKER_FLOW)
    public ResponseEntity<?> upload(@Valid @RequestBody BrokerDto clientRequest) {

        ResponseEntity<?> response;
        BrokerEntity brokerEntity;

        try {
            Optional<BrokerEntity> entity = brokerEntityFactory.getEntity(clientRequest);

            if(!entity.isPresent()) {
                throw new Exception("BrokerEntity not found");
            }

            brokerEntity = entity.get();

            logger.info("[{}] Request received for broker, entity: {}", brokerEntity.getUUID(), brokerEntity.getClass().getSimpleName());

            abstractTransferData.transferData(brokerEntity);
        } catch (Exception e) {
            logger.warn("Bad request {}: ", clientRequest, e);
            response = ResponseUtils.createErrorResponse(e);

            return response;
        }
        response = ResponseUtils.createOkResponse();

        return response;
    }
}
