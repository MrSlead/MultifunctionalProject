package com.almod.flow.broker.activemq.entity;

import com.almod.flow.broker.entity.AbstractClientRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ActiveMQClientRequest extends AbstractClientRequest {
    private String firstName;

    private String lastName;

    private String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private String serialNumber;

    private String placeOfResidence;
}
