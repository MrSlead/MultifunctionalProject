package com.almod.store.entity.broker.activemq;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class EmployeeEntity implements ActiveMQEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    Long id;

    @Builder.Default
    String UUID = GeneratorUUID.getUUID();

    String firstName;

    String lastName;

    String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateOfBirth;

    String serialNumber;

    String placeOfResidence;

    @Builder.Default
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeEntity")
    List<VacationEntity> vacationEntityList = new ArrayList<>();
}
