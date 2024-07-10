package com.almod.api.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ValidationErrorResponse {
    List<Violation> violations;
}
