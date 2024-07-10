package com.almod.api.controller;

import com.almod.api.util.ResponseUtils;
import com.almod.flow.AbstractTransferData;
import com.almod.store.entity.cache.CacheEntity;
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

@RestController
@Tag(name="Cache's flow", description="The flow for uploading data to the cache")
public class CacheController {
    private final Logger logger = LoggerFactory.getLogger(CacheController.class);

    private AbstractTransferData abstractTransferData;

    @Autowired
    public void setAbstractTransferDataToCache(@Qualifier("TransferDataToCache") AbstractTransferData abstractTransferData) {
        this.abstractTransferData = abstractTransferData;
    }

    public static final String CACHE_FLOW = "/api/flow/cache/upload";

    @PostMapping(CACHE_FLOW)
    public ResponseEntity<?> upload(@Valid @RequestBody CacheEntity cacheEntity) {
        logger.info("[{}] Request received for cache", cacheEntity.getUUID());

        ResponseEntity<?> response;

        try {
            abstractTransferData.transferData(cacheEntity);
        } catch (Exception e) {
            logger.warn("[{}] Bad request", cacheEntity.getUUID(), e);
            response = ResponseUtils.createErrorResponse(e);

            return response;
        }

        response = ResponseUtils.createOkResponse();

        return response;
    }
}
