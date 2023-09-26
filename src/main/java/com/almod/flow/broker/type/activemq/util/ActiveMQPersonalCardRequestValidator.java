package com.almod.flow.broker.type.activemq.util;

import com.almod.flow.broker.type.activemq.entity.ActiveMQPersonalCard;
import com.almod.common.exception.RequestValidationException;

/**
 * Альтернативный способ валидации
 */
public class ActiveMQPersonalCardRequestValidator {
    public static void validatorRequest(ActiveMQPersonalCard request) throws RequestValidationException {
        if (request.getFirstName() == null || request.getFirstName().isEmpty())  {
            throw new RequestValidationException("The firstName cannot be null or empty");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty())  {
            throw new RequestValidationException("The lastName cannot be null or empty");
        }

        if (request.getPatronymic() == null || request.getPatronymic() .isEmpty())  {
            throw new RequestValidationException("The patronymic cannot be null or empty");
        }

        if (request.getDateOfBirth() == null)  {
            throw new RequestValidationException("The date of birth cannot be null");
        }

        if (request.getSerialNumber() == null || request.getSerialNumber()  .isEmpty())  {
            throw new RequestValidationException("The serialNumber cannot be null or empty");
        }

        if (request.getPlaceOfResidence() == null || request.getPlaceOfResidence() .isEmpty())  {
            throw new RequestValidationException("The place of residence cannot be null or empty");
        }
    }
}
