package com.almod.store.entity.broker.activemq;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "vacation")
public class VacationEntity implements ActiveMQEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JsonIgnore
    @Builder.Default
    String UUID = GeneratorUUID.getUUID();

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startVacation;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endVacation;

    @JsonSetter("employee_id")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    ActiveMQEmployeeEntity activeMQEmployeeEntity;
}
