package com.almod.api.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Violation {
    String fieldName;
    String message;
}
