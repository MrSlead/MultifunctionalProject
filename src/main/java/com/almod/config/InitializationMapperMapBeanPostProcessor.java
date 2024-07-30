package com.almod.config;

import com.almod.api.factory.EntityFactory;
import com.almod.api.mapper.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
public class InitializationMapperMapBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Mapper) {
            Optional<? extends Class<?>> clazz = Arrays.stream(bean.getClass().getMethods())
                    .filter(method -> method.getName().equals("toDto"))
                    .map(Method::getReturnType)
                    .findFirst();

            if(clazz.isEmpty()) {
                return bean;
            }

            EntityFactory.getMapperMap().put(clazz.get(), (Mapper<?, ?>) bean);
        }
        return bean;
    }
}
