package com.almod.api.dto.broker.activemq;

import com.almod.api.dto.broker.BrokerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
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
public class ActiveMQEmployeeDto implements BrokerDto {
    @NotBlank(message = "The firstname cannot be null or empty")
    String firstName;

    @NotBlank(message = "The lastName cannot be null or empty")
    String lastName;

    String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The dateOfBirth cannot be null or empty")
    LocalDate dateOfBirth;

    @NotBlank(message = "The serialNumber cannot be null or empty")
    String serialNumber;

    @NotBlank(message = "The place Of residence cannot be null or empty")
    String placeOfResidence;
}
