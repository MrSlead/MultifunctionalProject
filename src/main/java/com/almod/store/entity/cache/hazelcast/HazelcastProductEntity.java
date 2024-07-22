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
@Table(name = "product")
public class HazelcastProductEntity implements HazelcastEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    Long id;

    @JsonIgnore
    @Builder.Default
    String UUID = GeneratorUUID.getUUID();

    String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateTimePurchase;

    @Column(length = 5000)
    String description;
}
