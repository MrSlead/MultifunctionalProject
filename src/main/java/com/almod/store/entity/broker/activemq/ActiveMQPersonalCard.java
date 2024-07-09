package com.almod.store.entity.broker.activemq;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "archive")
public class ActiveMQPersonalCard implements ActiveMQEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    String UUID = GeneratorUUID.getUUID();

    @NotBlank(message = "The firstname cannot be null or empty")
    String firstName;

    @NotBlank(message = "The lastName cannot be null or empty")
    String lastName;

    @NotBlank(message = "The patronymic cannot be null or empty")
    String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateOfBirth;

    @NotBlank(message = "The serialNumber cannot be null or empty")
    String serialNumber;

    @NotBlank(message = "The place Of residence cannot be null or empty")
    String placeOfResidence;
}
