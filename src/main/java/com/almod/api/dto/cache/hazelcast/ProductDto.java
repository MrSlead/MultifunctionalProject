package com.almod.api.dto.cache.hazelcast;

import com.almod.api.dto.cache.CacheDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto implements CacheDto {
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
