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
public class ActiveMQEmployeeEntity implements ActiveMQEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    // fix EAGER initialize
    @Builder.Default
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activeMQEmployeeEntity")
    List<VacationEntity> vacationEntityList = new ArrayList<>();
}
