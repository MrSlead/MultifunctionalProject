package com.almod.api.dto.broker.activemq;

import com.almod.api.dto.broker.BrokerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacationDto implements BrokerDto {
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The startVacation cannot be null or empty")
    LocalDate startVacation;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The endVacation cannot be null or empty")
    LocalDate endVacation;

    @JsonSetter("employee_id")
    @NotNull(message = "The employeeId cannot be null or empty")
    Long employeeId;
}


