package com.almod.api.controller;

import com.almod.api.dto.cache.CacheDto;
import com.almod.api.factory.EntityFactory;
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

import java.util.Optional;

@RestController
@Tag(name="Cache's flow", description="The flow for uploading data to the cache")
public class CacheController {
    private final Logger logger = LoggerFactory.getLogger(CacheController.class);

    private AbstractTransferData abstractTransferData;
    private EntityFactory cacheEntityFactory;

    @Autowired
    public void setAbstractTransferDataToCache(@Qualifier("TransferDataToCache") AbstractTransferData abstractTransferData) {
        this.abstractTransferData = abstractTransferData;
    }

    @Autowired
    public void setCacheEntityFactory(@Qualifier("CacheEntityFactory") EntityFactory cacheEntityFactory) {
        this.cacheEntityFactory = cacheEntityFactory;
    }

    public static final String CACHE_FLOW = "/api/flow/cache/upload";

    @PostMapping(CACHE_FLOW)
    public ResponseEntity<?> upload(@Valid @RequestBody CacheDto clientRequest) {

        ResponseEntity<?> response;
        CacheEntity cacheEntity;

        try {
            Optional<CacheEntity> entity = cacheEntityFactory.getEntity(clientRequest);

            if(!entity.isPresent()) {
                throw new Exception("CacheEntity not found");
            }

            cacheEntity = entity.get();

            logger.info("[{}] Request received for cache, entity: {}", cacheEntity.getUUID(), cacheEntity.getClass().getSimpleName());

            abstractTransferData.transferData(cacheEntity);
        } catch (Exception e) {
            logger.warn("Bad request {}: ", clientRequest, e);
            response = ResponseUtils.createErrorResponse(e);

            return response;
        }

        response = ResponseUtils.createOkResponse();

        return response;
    }
}
