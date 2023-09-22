package com.almod.flow.broker.activemq.entity;

import com.almod.flow.broker.entity.AbstractClientRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@Entity
@Table(name = "archive")
public class ActiveMQClientRequest extends AbstractClientRequest {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "The firstname cannot be null or empty")
    private String firstName;

    @NotBlank(message = "The lastName cannot be null or empty")
    private String lastName;

    @NotBlank(message = "The patronymic cannot be null or empty")
    private String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "The serialNumber cannot be null or empty")
    private String serialNumber;

    @NotBlank(message = "The place Of residence cannot be null or empty")
    private String placeOfResidence;
}
