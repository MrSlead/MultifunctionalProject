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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    LocalDateTime dateTime;

    String UUID;

    String errorText;

    @Lob
    @Column(length = 65535)
    String errorDetail;
}