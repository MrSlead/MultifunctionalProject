package com.almod.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
