package com.almod.flow.broker.common.entity;

import com.almod.common.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "error")
public class ErrorDB implements AbstractEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private LocalDateTime dateTime;

    private String UUID;

    private String errorText;

    @Lob
    @Column(length = 65535)
    private String errorDetail;
}