package com.almod.store.entity.cache.hazelcast;

import com.almod.util.GeneratorUUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "products")
public class HazelcastProductEntity implements HazelcastEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    @Builder.Default
    String UUID = GeneratorUUID.getUUID();

    String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateTimePurchase;

    @Lob
    @Column(length = 5000)
    String description;
}
