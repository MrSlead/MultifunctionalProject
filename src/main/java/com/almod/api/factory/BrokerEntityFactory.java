package com.almod.api.factory;

import com.almod.api.dto.broker.BrokerDto;
import com.almod.api.dto.broker.activemq.EmployeeDto;
import com.almod.api.dto.broker.activemq.VacationDto;
import com.almod.api.mapper.EmployeeMapper;
import com.almod.api.mapper.VacationMapper;
import com.almod.store.entity.broker.BrokerEntity;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.util.SpringApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("BrokerEntityFactory")
public class BrokerEntityFactory implements EntityFactory<BrokerDto, BrokerEntity> {
    @Override
    public Optional<BrokerEntity> getEntity(BrokerDto dto) {
        try {
            if(dto instanceof VacationDto) {
                var instance = (VacationMapper) SpringApplicationContext.getContext().getBean("vacationMapperImpl");

                Optional<VacationEntity> vacation = instance.toEntity((VacationDto) dto);

                return Optional.of(vacation.get());
            } else if(dto instanceof EmployeeDto) {
                var instance = (EmployeeMapper) SpringApplicationContext.getContext().getBean("employeeMapperImpl");

                EmployeeEntity employeeEntity = instance.toEntity((EmployeeDto) dto);

                return Optional.of(employeeEntity);
            }
            else
                throw new Exception("Unknown dto type: " + dto.getClass().getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
