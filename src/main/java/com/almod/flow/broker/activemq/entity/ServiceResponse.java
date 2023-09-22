package com.almod.flow.broker.activemq.entity;

import com.almod.flow.broker.entity.AbstractServiceResponse;

public class ServiceResponse extends AbstractServiceResponse {
    private final String status;
    private final String errorMsg;

    public ServiceResponse(ServiceResponseStatus status) {
        this(status, "");
    }

    public ServiceResponse(ServiceResponseStatus status, String errorMsg) {
        this.status = status.toString();
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status='" + status + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public enum ServiceResponseStatus {
        s, // Сервис обработал запрос успешно
        e // Ошибка во время обработки запроса сервисом
    }
}
