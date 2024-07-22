package com.almod.api.mapper;

import com.almod.api.dto.broker.activemq.VacationDto;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.store.service.broker.activemq.ActiveMQEmployeeService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class VacationMapper {

    protected ActiveMQEmployeeService activeMQEmployeeService;

    @Autowired
    public void setActiveMQEmployeeService(ActiveMQEmployeeService activeMQEmployeeService) {
        this.activeMQEmployeeService = activeMQEmployeeService;
    }

    public VacationDto toDto(VacationEntity vacationEntity) {
        VacationDto dto = new VacationDto().builder()
                .startVacation(vacationEntity.getStartVacation())
                .endVacation(vacationEntity.getEndVacation())
                .employeeId(vacationEntity.getActiveMQEmployeeEntity().getId()).build();

        return dto;

    }

    public Optional<VacationEntity> toEntity(VacationDto vacationDto) {
        Optional<ActiveMQEmployeeEntity> activeMQEmployeeEntity = activeMQEmployeeService.findById(vacationDto.getEmployeeId());

        if(activeMQEmployeeEntity.isEmpty()) {
            return Optional.empty();
        }

        Optional<VacationEntity> vacation = Optional.ofNullable(new VacationEntity().builder()
                .startVacation(vacationDto.getStartVacation())
                .endVacation(vacationDto.getEndVacation())
                .activeMQEmployeeEntity(activeMQEmployeeEntity.get()).build());

        return vacation;
    }
}
