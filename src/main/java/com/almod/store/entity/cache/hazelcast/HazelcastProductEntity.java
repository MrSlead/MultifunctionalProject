package com.almod.store.entity.cache.hazelcast;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "products")
public class HazelcastProductEntity implements HazelcastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    Long id;

    @JsonIgnore
    String UUID = GeneratorUUID.getUUID();

    @NotBlank(message = "The name cannot be null or empty")
    String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "The dateTimePurchase cannot be null or empty")
    LocalDateTime dateTimePurchase;

    @Lob
    @Column(length = 5000)
    @NotBlank(message = "The description cannot be null or empty")
    String description;
}
