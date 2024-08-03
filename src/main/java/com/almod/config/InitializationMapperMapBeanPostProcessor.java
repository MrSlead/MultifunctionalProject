package com.almod.config;

import com.almod.api.annotation.TakeReturnType;
import com.almod.api.factory.EntityFactory;
import com.almod.api.mapper.Mapper;
import org.apache.commons.lang3.ClassUtils;
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
            Optional<String> methodName = ClassUtils.getAllInterfaces(bean.getClass()).stream()
                    .filter(i -> i.isAssignableFrom(Mapper.class))
                    .flatMap(i -> Arrays.stream(i.getMethods()))
                    .filter(method -> method.getAnnotation(TakeReturnType.class) != null)
                    .map(Method::getName)
                    .findFirst();

            if(methodName.isEmpty()) {
                return bean;
            }

            Optional<? extends Class<?>> dtoClass = Arrays.stream(bean.getClass().getMethods())
                    .filter(method -> method.getName().equals(methodName.get()))
                    .map(Method::getReturnType)
                    .findFirst();

            if(dtoClass.isEmpty()) {
                return bean;
            }

            EntityFactory.getMapperMap().put(dtoClass.get(), (Mapper<?, ?>) bean);
        }
        return bean;
    }
}
