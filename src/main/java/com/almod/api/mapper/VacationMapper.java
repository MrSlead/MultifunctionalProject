package com.almod.api.mapper;

import com.almod.api.dto.broker.activemq.VacationDto;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import com.almod.store.service.broker.activemq.EmployeeService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class VacationMapper implements com.almod.api.mapper.Mapper<VacationDto, VacationEntity> {

    protected EmployeeService employeeService;

    @Autowired
    public void setActiveMQEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public VacationDto toDto(VacationEntity vacationEntity) {
        VacationDto dto = new VacationDto().builder()
                .startVacation(vacationEntity.getStartVacation())
                .endVacation(vacationEntity.getEndVacation())
                .employeeId(vacationEntity.getEmployeeEntity().getId()).build();

        return dto;

    }

    public VacationEntity toEntity(VacationDto vacationDto) {
        Optional<EmployeeEntity> activeMQEmployeeEntity = employeeService.findById(vacationDto.getEmployeeId());

//        if(activeMQEmployeeEntity.isEmpty()) {
//            return Optional.empty();
//        }

        VacationEntity vacation = new VacationEntity().builder()
                .startVacation(vacationDto.getStartVacation())
                .endVacation(vacationDto.getEndVacation())
                .employeeEntity(activeMQEmployeeEntity.get()).build();

        return vacation;
    }
}
