package com.almod.api.dto.broker;

import com.almod.api.dto.broker.activemq.EmployeeDto;
import com.almod.api.dto.broker.activemq.VacationDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeDto.class),
        @JsonSubTypes.Type(value = VacationDto.class)
})
public interface BrokerDto {
}
