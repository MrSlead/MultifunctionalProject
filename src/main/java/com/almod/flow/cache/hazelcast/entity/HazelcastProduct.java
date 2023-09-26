package com.almod.flow.cache.hazelcast.entity;

import com.almod.common.entity.AbstractEntity;
import com.almod.common.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class HazelcastProduct implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String UUID = GeneratorUUID.getUUID();

    private String name;

    private LocalDateTime dateTime;

    @Lob
    @Column(length = 5000)
    private String description;
}
