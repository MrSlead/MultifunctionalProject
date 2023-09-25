package com.almod.flow.broker.activemq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Singleton класса ObjectMapper для работы с LocalDate при серилизации/десерилизации json
 */
public class ObjectMapperSingleton {
    private static ObjectMapper objectMapper;

    private ObjectMapperSingleton() {}

    public static ObjectMapper getCustomizedObjectMapper() {
        if(objectMapper == null)
            return (objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build());
        else
            return objectMapper;
    }
}
