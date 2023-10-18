package com.almod.flow.cache.hazelcast.controller;

import com.almod.common.entity.ServiceResponse;
import com.almod.flow.cache.hazelcast.config.ClientConfigHazelcast;
import com.almod.flow.cache.hazelcast.entity.HazelcastProduct;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HazelcastController {
    private final Logger LOGGER = LoggerFactory.getLogger(HazelcastController.class);

    private ClientConfigHazelcast clientConfigHazelcast;

    @Autowired
    public void setClientConfigHazelcast(ClientConfigHazelcast clientConfigHazelcast) {
        this.clientConfigHazelcast = clientConfigHazelcast;
    }

    @PostMapping("/hazelcast")
    public ResponseEntity<ServiceResponse> upload(@RequestBody HazelcastProduct hazelcastProduct) {
        LOGGER.info(String.format("[%s] Request received for Hazelcast", hazelcastProduct.getUUID()));

        ResponseEntity<ServiceResponse> response;

        try {
            IMap<Object, Object> map = clientConfigHazelcast.getMap();
            map.putAsync(hazelcastProduct.getUUID(), hazelcastProduct.toString());
            LOGGER.info(String.format("[%s] Success inserted into the cache", hazelcastProduct.getUUID()));
        } catch (Exception e) {
            LOGGER.warn(String.format("[%s] Bad request", hazelcastProduct.getUUID()));
            e.printStackTrace();
            response = ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));

            return response;
        }

        response = ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));

        return response;
    }

    @GetMapping("/hztest")
    public ResponseEntity<?> health() {

        IMap<Object, Object> map = clientConfigHazelcast.getMap();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10; i++) {
            map.putAsync(i, i*i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println(elapsed);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
