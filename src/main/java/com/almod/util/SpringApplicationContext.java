package com.almod.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Класс для получения ApplicationContext в любом месте приложения
 */
@Component
public class SpringApplicationContext {
    @Getter
    private static ApplicationContext context;

    @Autowired
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationContext.context = context;
    }
}