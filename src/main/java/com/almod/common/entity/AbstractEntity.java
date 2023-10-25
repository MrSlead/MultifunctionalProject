package com.almod.common.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
public interface AbstractEntity extends Serializable {
    Long getId();
    String getUUID();
}
