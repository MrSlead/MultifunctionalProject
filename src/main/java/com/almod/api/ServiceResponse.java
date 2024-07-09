package com.almod.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceResponse  {
    String status;
    String errorMsg;

    public ServiceResponse(ServiceResponseStatus status) {
        this(status, "");
    }

    public ServiceResponse(ServiceResponseStatus status, String errorMsg) {
        this.status = status.toString();
        this.errorMsg = errorMsg;
    }

    public enum ServiceResponseStatus {
        s, // Сервис обработал запрос успешно
        e // Ошибка во время обработки запроса сервисом
    }
}
