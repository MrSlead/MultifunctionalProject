package com.almod.api.util;

import com.almod.api.response.ServiceResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<ServiceResponse> createOkResponse() {
        return ResponseEntity.ok().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.s));
    }

    public static ResponseEntity<?> createErrorResponse(Exception e) {
        return ResponseEntity.badRequest().body(new ServiceResponse(ServiceResponse.ServiceResponseStatus.e, e.getMessage()));
    }
}
