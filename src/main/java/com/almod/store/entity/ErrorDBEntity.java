package com.almod.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "error")
public class ErrorDBEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "error_seq")
    @SequenceGenerator(name = "error_seq", sequenceName = "error_seq", allocationSize = 1)
    Long id;

    String UUID;

    LocalDateTime dateTime;

    String errorText;

    @Column(length = 65535)
    String errorDetail;
}