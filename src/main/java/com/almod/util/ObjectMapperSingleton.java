package com.almod.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Singleton класса ObjectMapper для работы с LocalDate при серилизации/десерилизации json
 */
public class ObjectMapperSingleton {
    private static final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public static ObjectMapper getCustomizedObjectMapper() {
        return objectMapper;
    }
}
