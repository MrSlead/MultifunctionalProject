package com.almod.api.controller;

import com.almod.api.ServiceResponse;
import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.cache.CacheEntity;
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
public class CacheController {
    private final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    private AbstractTransferData abstractTransferData;

    @Autowired
    public void setAbstractTransferDataToCache(@Qualifier("TransferDataToCache") AbstractTransferData abstractTransferData) {
        this.abstractTransferData = abstractTransferData;
    }

    @PostMapping("/cache")
    public ResponseEntity<ServiceResponse> upload(@Valid @RequestBody CacheEntity cacheEntity) {
        LOGGER.info(String.format("[%s] Request received for cache", cacheEntity.getUUID()));

        ResponseEntity<ServiceResponse> response;

        try {
            abstractTransferData.transferData(cacheEntity);
        } catch (Exception e) {
            LOGGER.warn(String.format("[%s] Bad request", cacheEntity.getUUID()));
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }

        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }
}
