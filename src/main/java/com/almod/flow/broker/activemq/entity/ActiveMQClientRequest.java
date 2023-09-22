package com.almod.flow.broker.activemq.entity;

import com.almod.flow.broker.entity.AbstractClientRequest;

public class ActiveMQClientRequest extends AbstractClientRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dateOfBirth;
    private String serialNumber;
    private String placeOfResidence;

    public ActiveMQClientRequest(String firstName, String lastName, String patronymic, String dateOfBirth, String serialNumber, String placeOfResidence) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.serialNumber = serialNumber;
        this.placeOfResidence = placeOfResidence;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", placeOfResidence='" + placeOfResidence + '\'' +
                '}';
    }
}
