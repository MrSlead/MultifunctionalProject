package com.almod.api.mapper;

import com.almod.api.dto.broker.activemq.ActiveMQEmployeeDto;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActiveMQEmployeeMapper {
    ActiveMQEmployeeDto toDto(ActiveMQEmployeeEntity entity);
    ActiveMQEmployeeEntity toEntity(ActiveMQEmployeeDto dto);
}
