package com.almod.common.entity;

import java.io.Serializable;

public interface AbstractEntity extends Serializable {
    Long getId();
    String getUUID();
}
