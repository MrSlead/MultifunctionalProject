package com.almod.flow.cache.hazelcast.entity;

import com.almod.common.util.GeneratorUUID;
import com.almod.flow.cache.common.entity.HazelcastEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class HazelcastProduct implements HazelcastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String UUID = GeneratorUUID.getUUID();

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateTimePurchase;

    @Lob
    @Column(length = 5000)
    private String description;
}
