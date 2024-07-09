package com.almod.store.entity.cache.hazelcast;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "The name cannot be null or empty")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "The dateTimePurchase cannot be null or empty")
    private LocalDateTime dateTimePurchase;

    @Lob
    @Column(length = 5000)
    @NotBlank(message = "The description cannot be null or empty")
    private String description;
}
