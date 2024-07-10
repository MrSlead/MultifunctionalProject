package com.almod.api.controller;

import com.almod.api.response.ValidationErrorResponse;
import com.almod.api.response.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Общий обработчик ошибок для сущностей с полями, помеченными аннотацией @NotBlank
 * Для проверки переменных пути и параметров необходимо добавить обработчик ошибки ConstraintViolationException.class
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    final private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ValidationErrorResponse(violations);
    }
}
