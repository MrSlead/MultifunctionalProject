package com.almod.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="The life of the service", description="Checking that the service is available")
public class ServiceController {
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
